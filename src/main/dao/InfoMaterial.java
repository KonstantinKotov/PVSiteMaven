package dao;


public class InfoMaterial {

    private int materialID;
    private String materilName;
    private String materialDescription;

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getMaterilName() {
        return materilName;
    }

    public void setMaterilName(String materilName) {
        this.materilName = materilName;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public boolean equals(InfoMaterial infoMat){
        boolean status = false;
        if(this.materialID == infoMat.materialID && this.materialDescription.equals(infoMat.getMaterialDescription()) &&
                this.materilName.equals(infoMat.getMaterilName()));{
            status = true;
        }
        return status;
    }


}
