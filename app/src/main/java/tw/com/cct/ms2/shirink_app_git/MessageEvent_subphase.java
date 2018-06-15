package tw.com.cct.ms2.shirink_app_git;

public class MessageEvent_subphase {
    public int subphase;
    public void  setsubphase(int Stepnum){this.subphase=Stepnum;}
    public int getsubphase(){return this.subphase;}
    public MessageEvent_subphase(int subphase)
    {
        this.subphase=subphase;
    }
}
