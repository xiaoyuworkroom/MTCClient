package xiaoyu.mtc.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SamplingManager {

    // <editor-fold desc="单例">

    private static SamplingManager instance = new SamplingManager();

    private SamplingManager() {
    }

    public static SamplingManager getInstance() {
        return instance;
    }

    // </editor-fold>

    public List<Integer> Data = new ArrayList();

    public boolean getIsStart() {
        return isStart;
    }

    private boolean isStart = false;

    public void Initialize(){
        isStart = false;
        Data.clear();
    }

    public void Start(){
        isStart = true;
    }

    public void Stop(){
        isStart = false;
    }

    public boolean addDataItem(Integer v){
        if(isStart){
            Data.add(v);
        }
        return false;
    }
}
