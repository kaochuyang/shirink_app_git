package tw.com.cct.ms2.shirink_app_git;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MessageEvent {
    public int Segmenttype;

    public MessageEvent(int segmenttype)
    {
        this.Segmenttype=segmenttype;
    }

    public int getSegmenttype() {
        return Segmenttype;
    }

    public void setSegmenttype(int segmenttype)
    {
        this.Segmenttype=segmenttype;
    }



}
