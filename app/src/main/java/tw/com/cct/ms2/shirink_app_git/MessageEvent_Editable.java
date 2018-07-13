package tw.com.cct.ms2.shirink_app_git;

public class MessageEvent_Editable {
    public boolean StepEditable;
    public void  setStepEditable(boolean EditableFlag){this.StepEditable=EditableFlag;}
    public boolean getStepEditable(){return this.StepEditable;}
    public MessageEvent_Editable(boolean EditableFlag)
    {
        this.StepEditable=EditableFlag;
    }
}
