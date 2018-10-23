package udf.app;
 
 
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
 
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardListObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
 
 
 
 
public class firstLastAmountUDAF extends AbstractGenericUDAFResolver {
    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] tis)
            throws SemanticException
    {
        return new firstLastAmountUDAFEvaluator();
    }

	public static class firstLastAmountUDAFEvaluator extends GenericUDAFEvaluator {
        private PrimitiveObjectInspector inputOI;
        private StandardListObjectInspector loi;
        private StandardListObjectInspector internalMergeOI;
        
		private Tuple resultTuple = new Tuple();
		private String typeSameDay;
 
 
        static class ArrayAggregationBuffer implements AggregationBuffer
        {
            ArrayList<Double> container;
        }	
        @Override
        public void reset(AggregationBuffer ab)
                throws HiveException
        {
            ((ArrayAggregationBuffer) ab).container = new ArrayList<Double>();
        }
 
 
        @Override
        public AggregationBuffer getNewAggregationBuffer()
                throws HiveException
        {
            ArrayAggregationBuffer ret = new ArrayAggregationBuffer();
            reset(ret);
            return ret;
        }
 
 
		public static class Tuple {
//		Caused by: java.lang.NoSuchMethodException: com.hive.prod.udaf.prod_topkUDAF$prod_topkUDAFEvaluator$Tuple.<init>()
			  public String minCreated;
			  public double minAmount;
			  public String maxCreated;
			  public double maxAmount;
		}
 
 
	       public ObjectInspector init(Mode m, ObjectInspector[] parameters)
	                throws HiveException
	        {
	 	        resultTuple.minCreated="1900-01-01";
	            super.init(m, parameters);
	            if (m == Mode.PARTIAL1)
	            {
	                inputOI = (PrimitiveObjectInspector) parameters[0];
	                return ObjectInspectorFactory.getStandardListObjectInspector((PrimitiveObjectInspector) ObjectInspectorUtils.getStandardObjectInspector(inputOI));
	            }
	            else
	            {
	                if (!(parameters[0] instanceof StandardListObjectInspector))
	                {
	                    inputOI = (PrimitiveObjectInspector)  ObjectInspectorUtils.getStandardObjectInspector(parameters[0]);
	                    return (StandardListObjectInspector) ObjectInspectorFactory.getStandardListObjectInspector(inputOI);
	                }
	                else
	                {
	                    internalMergeOI = (StandardListObjectInspector) parameters[0];
	                    inputOI = (PrimitiveObjectInspector) internalMergeOI.getListElementObjectInspector();
	                    loi = (StandardListObjectInspector) ObjectInspectorUtils.getStandardObjectInspector(internalMergeOI);
	                    return loi;
	                }
	            }
	        }
		
		public void iterate(AggregationBuffer ab, Object[] arg) {
			//type='merge' 合并 ；type='single' 不用合并 
			if (arg[0]==null){
				return;
			}
			String created=arg[0].toString();
			double amount=Double.parseDouble(arg[1].toString());
			String type=arg[2].toString();
			
			typeSameDay=type;
	        String eL= "^\\d{4}-\\d{2}-\\d{2}$";   
	        Pattern p = Pattern.compile(eL);    
	        Matcher m = p.matcher(created);    
	        boolean b = m.matches();   	
	        if(!b){
	        	return ;
	        }
			if(resultTuple.minCreated.equals("1900-01-01")){
				resultTuple.minCreated=created;
				resultTuple.minAmount=amount;
				resultTuple.maxCreated=created;
				resultTuple.maxAmount=amount;		
				return ;
			}
			if(resultTuple.minCreated.compareTo(created)>0){
				resultTuple.minCreated=created;
				resultTuple.minAmount=amount;
				return ;
			}
			if(resultTuple.maxCreated.compareTo(created)<0){
				resultTuple.maxCreated=created;
				resultTuple.maxAmount=amount;
				return ;
			}
			if(typeSameDay.equalsIgnoreCase("merge") ){
				//同一天的进行合并
				if(resultTuple.maxCreated.compareTo(created)==0){
					resultTuple.maxAmount+=amount;	
				}
				if(resultTuple.minCreated.compareTo(created)==0){
					resultTuple.minAmount+=amount;
				}
			}
			return ;
		}
 
 
		public Object terminatePartial(AggregationBuffer ab) {
			return resultTuple;
		}
 
 
		public void merge(AggregationBuffer ab, Object o) {
			Tuple other = (Tuple) o;
			if (other.minCreated.equals("1900-01-01")) {
				return;
			}
			if (resultTuple.minCreated.equals("1900-01-01")) {
				resultTuple.minCreated=other.minCreated;
				resultTuple.minAmount=other.minAmount;
				resultTuple.maxCreated=other.maxCreated;
				resultTuple.maxAmount=other.maxAmount;				
				return;
			}			
			if(resultTuple.minCreated.compareTo(other.minCreated)>0){
				resultTuple.minCreated=other.minCreated;
				resultTuple.minAmount=other.minAmount;
			}
			if(resultTuple.maxCreated.compareTo(other.maxCreated)<0){
				resultTuple.maxCreated=other.maxCreated;
				resultTuple.maxAmount=other.maxAmount;	
			}
			if(typeSameDay.equalsIgnoreCase("merge") ){
				//同一天不进行合并
				if(resultTuple.maxCreated.compareTo(other.maxCreated)==0){
					resultTuple.maxAmount+=other.maxAmount;	
				}
				if(resultTuple.minCreated.compareTo(other.minCreated)==0){
					resultTuple.minAmount+=other.minAmount;
				}
			}
			return ;
		}
 
 
		public  ArrayList<Double> terminate(AggregationBuffer ab) {
			if (resultTuple.minCreated.equals("1900-01-01")) {
				return null;
			}
            ArrayList<Double> ret = new ArrayList<Double>(2);
            ret.set(0, resultTuple.minAmount);
            ret.set(1, resultTuple.maxAmount);
            return ret;
		}
	}
	
}
