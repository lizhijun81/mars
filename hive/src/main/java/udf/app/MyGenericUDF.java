package udf.app;

import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

import java.util.List;

/**
 GenericUDF的使用场景:
     org.apache.hadoop.hive.ql.udf.generic.GenericUDF API提供了一种方法去处理那些不是可写类型的对象，例如：struct，map和array类型。
     这个API需要你亲自去为函数的参数去管理对象存储格式（object inspectors），验证接收的参数的数量与类型。一个object inspector为内在的数据类型提供一个一致性接口，以至不同实现的对象可以在hive中以一致的方式去访问（例如，只要你能提供一个对应的object inspector，你可以实现一个如Map的复合对象）。

 GenericUDF函数回调时机
     // 只调用一次，在任何evaluate()调用之前，你可以接收到一个可以表示函数输入参数类型的object inspectors数组
     // 这是你用来验证该函数是否接收正确的参数类型和参数个数的地方
     abstract ObjectInspector initialize(ObjectInspector[] arguments);

    // 这个类似于简单API的evaluat方法，它可以读取输入数据和返回结果
    abstract Object evaluate(GenericUDF.DeferredObject[] arguments);

    // 该方法无关紧要，我们可以返回任何东西，但应当是描述该方法的字符串
    abstract String getDisplayString(String[] children);

 * /


/**
 * 判断 hive 数组中是否存在 某个字符串
 */
public class MyGenericUDF extends GenericUDF {

    private double sumSalary = 0D;

    public MyGenericUDF() {}

    private ListObjectInspector listOI;

    private StringObjectInspector elementOI;

    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

        ObjectInspector a = arguments[0];
        ObjectInspector b = arguments[1];

        listOI = (ListObjectInspector) a; // 指定 udf 的入参格式
        elementOI = (StringObjectInspector) b;// 指定 udf 的入参格式

        return PrimitiveObjectInspectorFactory.javaBooleanObjectInspector;// 指定 udf 返回返回类型
    }

    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        List<String> list = (List<String>) this.listOI.getList(arguments[0].get());

        String str = this.elementOI.getPrimitiveJavaObject(arguments[1].get());

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.contains(str);
    }

    /**
     * mapperReduce task 内部回调 显示
     */
    public String getDisplayString(String[] children) {
        return "this is first MyGenericUDF";
    }

}
