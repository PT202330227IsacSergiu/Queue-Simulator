import GUI.ControllerInputs;
import GUI.FrameGetInputData;
import Logic.SelectionPolicy;
import Tests.Test;

public class MainClass {

    public static void main(String[] argc){
        FrameGetInputData view = new FrameGetInputData();
        ControllerInputs ctrl = new ControllerInputs(view, "log.txt");

//        Test testTime = new Test();
//        testTime.test(60, 2, 4, 4, 2, 10,4, SelectionPolicy.SHORTEST_TIME, "test1.txt");
//        testTime.test(60, 5, 50, 7, 1, 40,2, SelectionPolicy.SHORTEST_TIME, "test2.txt");
//        testTime.test(200, 20, 1000, 9, 3, 100,10, SelectionPolicy.SHORTEST_TIME, "test3.txt");

    }
}
