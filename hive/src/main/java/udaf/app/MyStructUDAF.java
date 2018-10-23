package udaf.app;

import com.google.common.collect.Lists;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.*;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * udaf 返回复杂类型
 */
@Description(name = "myStruct", value = "_FUNC_ ", extended = "udaf return a struct ")
public class MyStructUDAF extends AbstractGenericUDAFResolver {

    private static Logger logger = LoggerFactory.getLogger(MyStructUDAF.class);

    public MyStructUDAF() {
        super();
    }

    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] info) throws SemanticException {
        return new MyStructUDAFEvaluator();
    }

    static class MyStructUDAFEvaluator extends GenericUDAFEvaluator {

        public MyStructUDAFEvaluator() {}

        // AggregationBuffer 允许我们保存中间结果
        static class MyStructAggregationBuffer extends AbstractAggregationBuffer {
            private double value = Double.MIN_VALUE;
            private String name = "";
            private int age = 0;

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public void reset() {
                this.value = Double.MIN_VALUE;
                this.name = "";
                this.age = 0;
            }
        }

        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            return new MyStructAggregationBuffer();
        }

        @Override
        public void reset(AggregationBuffer agg) throws HiveException {
            MyStructAggregationBuffer buffer = (MyStructAggregationBuffer) agg;
            buffer.reset();
        }

        private StringObjectInspector nameInputOI;
        private DoubleObjectInspector salaryInputIO;
        private IntObjectInspector ageInputIO;

        private StandardStructObjectInspector reduceInputIO;

        private StandardStructObjectInspector result;

        @Override
        public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
            List<String> fieldNames = Arrays.asList("name",  "salary", "age");

            List<ObjectInspector> fieldStruct = Lists.newArrayListWithCapacity(3);

            fieldStruct.add(PrimitiveObjectInspectorFactory.writableStringObjectInspector);
            fieldStruct.add(PrimitiveObjectInspectorFactory.writableDoubleObjectInspector);
            fieldStruct.add(PrimitiveObjectInspectorFactory.writableIntObjectInspector);

            if (m == Mode.PARTIAL1 || m == Mode.COMPLETE) {
                nameInputOI = (StringObjectInspector) parameters[0];
                salaryInputIO = (DoubleObjectInspector) parameters[1];
                ageInputIO = (IntObjectInspector) parameters[2];
            } else {
                logger.info("init reduceInputIO is exec");
                reduceInputIO = (StandardStructObjectInspector) parameters[0];
            }
            result = ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldStruct);

            return result;
        }

        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {// 处理 mapper 阶段的输入，输入类型为 sql 中的 的输入参数

            logger.info("info logger======parameters======name:" + parameters[0].toString() + ", salary:" + parameters[1].toString() +", age:" + parameters[2]);

            String name = nameInputOI.getPrimitiveJavaObject(parameters[0]);
            double salary = salaryInputIO.get(parameters[1]);
            int age = ageInputIO.get(parameters[2]);

            logger.info("info logger======iterate======name:" + name + ", salary:" + salary +", age:" + age);
            System.out.println("system======iterate======name:" + name + ", salary:" + salary +", age:" + age);

            MyStructAggregationBuffer buffer = (MyStructAggregationBuffer) agg;



            if (salary > buffer.getValue()) {
                buffer.setName(name);
                buffer.setAge(age);
                buffer.setValue(salary);
            }

        }

        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            return terminate(agg);
        }

        @Override
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {// 处理reduce 阶段的输入，输入类型为 struct,即 为 reduceInputIO

            if (Objects.isNull(reduceInputIO)) {
                logger.info("reduceInputIO is null");
            }

            if (Objects.nonNull(partial) && reduceInputIO instanceof StandardStructObjectInspector) {
//                LazyString nameLazyString = (LazyString) reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("name"));
//                LazyDouble salaryLazyDouble = (LazyDouble) reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("salary"));
//                LazyInteger ageLazyInteger = (LazyInteger) reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("age"));
////


                MyStructAggregationBuffer buffer = (MyStructAggregationBuffer) agg;
//
//                String name = ((WritableStringObjectInspector) reduceInputIO.getStructFieldRef("name").getFieldObjectInspector()).getPrimitiveJavaObject(nameLazyString);
//                double salary = ((WritableDoubleObjectInspector) reduceInputIO.getStructFieldRef("salary").getFieldObjectInspector()).get(salaryLazyDouble);
//                int age = ((WritableIntObjectInspector) reduceInputIO.getStructFieldRef("age").getFieldObjectInspector()).get(ageLazyInteger);


                Object n = reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("name"));
                Object s = reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("salary"));
                Object a = reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("age"));


                String name = ((WritableStringObjectInspector) reduceInputIO.getStructFieldRef("name").getFieldObjectInspector()).getPrimitiveJavaObject(n);
                double salary = ((WritableDoubleObjectInspector) reduceInputIO.getStructFieldRef("salary").getFieldObjectInspector()).get(s);
                int age = ((WritableIntObjectInspector) reduceInputIO.getStructFieldRef("age").getFieldObjectInspector()).get(a);


//                String name = nameInputOI.getPrimitiveJavaObject(reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("name")));
//                double salary = salaryInputIO.get(reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("salary")));
//                int age = ageInputIO.get(reduceInputIO.getStructFieldData(partial, reduceInputIO.getStructFieldRef("age")));


                logger.info("info logger======iterate======name:" + name + ", salary:" + salary +", age:" + age);
                System.out.println("system======merge======name:" + name + ", salary:" + salary +", age:" + age);

                if (salary > buffer.getValue()) {

                    buffer.setName(name);
                    buffer.setValue(salary);
                    buffer.setAge(age);
                }
            }
        }

        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {
            MyStructAggregationBuffer buffer = (MyStructAggregationBuffer) agg;

            logger.info("info logger======terminate======name:" + buffer.getName() + ", salary:" + buffer.getValue() +", age:" + buffer.getAge());
            System.out.println("system======terminate======name:" + buffer.getName() + ", salary:" + buffer.getValue() +", age:" + buffer.getAge());

            List<Object> info = Lists.newArrayList();

            info.add(new Text(buffer.getName()));
            info.add(new DoubleWritable(buffer.getValue()));
            info.add(new IntWritable(buffer.getAge()));

            return info.toArray();
        }

    }

}
