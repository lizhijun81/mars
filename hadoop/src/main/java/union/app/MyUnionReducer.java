package union.app;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.Iterator;

public class MyUnionReducer extends Reducer<MyUnionCombineKey, Text, Text, Text> {

    @Override
    protected void reduce(MyUnionCombineKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();

        Text station = new Text(iterator.next());
        System.out.println("station" + station);

        while (iterator.hasNext()) {
            Text temp = iterator.next();
            System.out.println("detail:" + station.toString() + "========" + temp.toString());
            context.write(station, new Text(temp.toString()));
        }
    }

}
