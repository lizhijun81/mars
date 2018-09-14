package udaf.app;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.DoubleWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

@Description(name = "myMax", value = "_FUNC_ name myMax", extended = "this is myMax")
public class MyMaxUDAF extends AbstractGenericUDAFResolver {

    private static Logger logger = LoggerFactory.getLogger(MyMaxUDAF.class);

    public MyMaxUDAF() {
        super();
    }

    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] info) throws SemanticException {
        return new MyMaxUDAFEvaluator();
    }

    static class MyMaxUDAFEvaluator extends GenericUDAFEvaluator {

        public MyMaxUDAFEvaluator() {}

        // AggregationBuffer 允许我们保存中间结果
        static class MyMaxAggregationBuffer extends AbstractAggregationBuffer {
            private double value = Double.MIN_VALUE;

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }

        // 每次遇到一个不同的key，则新建一个AggregationBuffer
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            logger.info("====init aggregation buffer=====");
            System.out.println("====init aggregation buffer=====");
            return new MyMaxAggregationBuffer();
        }

        //
        public void reset(AggregationBuffer agg) throws HiveException {
            MyMaxAggregationBuffer agg_1 = (MyMaxAggregationBuffer) agg;
            logger.info(agg + "====reset=====" + agg_1.getValue());
            System.out.println(agg + "====reset=====" + agg_1.getValue());
            agg_1.setValue(Double.MIN_VALUE);
        }

        private DoubleObjectInspector inputObjectInspector;

        private DoubleObjectInspector outputObjectInspector_1;

        private DoubleObjectInspector outputObjectInspector;

        /**
         * init 的返回值类型为 terminatePartial、terminate 的返回值类型
         */
        @Override
        public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
            super.init(m, parameters);
            if (m == Mode.PARTIAL1 || m == Mode.COMPLETE) {
                // hadoop job 所处的阶段，PARTIAL1 为 mapper 的 map 和 combin 两个阶段，这个 PARTIAL1 阶段的设置的输入参数依据为hql中的udtf，入参也为iterate入参，输出为参数类型为terminatePartial返回值类型
                inputObjectInspector = (DoubleObjectInspector) parameters[0]; // mapper 阶段的输入参数， select 中 udaf 的入参，计算平均值，故，入参是double的数值
            } else {// redurce 阶段的 parameters 值只有一个，因为 mapper 阶段的 输出值 只有一个
                outputObjectInspector_1 = (DoubleObjectInspector) parameters[0];// reduce 阶段的输入 是 mapper 的输出， mapper 中输出为 double，故，reduce 的输入为 double
            }
            outputObjectInspector = PrimitiveObjectInspectorFactory.writableDoubleObjectInspector;

            return outputObjectInspector;
        }

        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
            MyMaxAggregationBuffer agg_1 = (MyMaxAggregationBuffer) agg;
            double inputValue = inputObjectInspector.get(parameters[0]);
            logger.info(agg + "====iterate=====" + agg_1.getValue());
            System.out.println(agg + "====iterate=====" + agg_1.getValue());
            if (agg_1.getValue() < inputValue) {
                agg_1.setValue(inputValue);
            }
        }

        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            MyMaxAggregationBuffer agg_1 = (MyMaxAggregationBuffer) agg;
            logger.info(agg + "====terminatePartial=====" + agg_1.getValue());
            System.out.println(agg + "====terminatePartial=====" + agg_1.getValue());
            return terminate(agg);
        }

        // partial 为 reduce 的输入参数
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {
            if (Objects.nonNull(partial)) {
                MyMaxAggregationBuffer agg_1 = (MyMaxAggregationBuffer) agg;
                logger.info(agg + "====merge=====" + agg_1.getValue());
                System.out.println(agg + "====merge=====" + agg_1.getValue());
                double max_value = outputObjectInspector_1.get(partial);
                if (max_value > agg_1.getValue()) {
                    agg_1.setValue(max_value);
                }
            }
        }

        public Object terminate(AggregationBuffer agg) throws HiveException {
            MyMaxAggregationBuffer agg_1 = (MyMaxAggregationBuffer) agg;
            logger.info(agg + "====terminate=====" + agg_1.getValue());
            System.out.println(agg + "====terminate=====" + agg_1.getValue());
            return new DoubleWritable(agg_1.getValue());
        }
    }

}
