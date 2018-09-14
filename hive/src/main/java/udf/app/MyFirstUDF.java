package udf.app;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(name = "firstUDF", value = "_FUNC_ is firstUDF, concat many columns", extended = "Example:\nSELECT _FUNC_(name, address, salary) FROM table_name")
public class MyFirstUDF extends UDF {

    public MyFirstUDF() {
    }

    /**
     * 每行记录都需要回调该方法
     */
    public String evaluate(String name, String address, double salary) {
        return name + "_" + address + "_" + salary;
    }

}
