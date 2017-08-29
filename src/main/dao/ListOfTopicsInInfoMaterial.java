package dao;

/**
 * Created by k.kotov on 16.07.2017.
 */
public class ListOfTopicsInInfoMaterial {

    private int listID;
    private int listMaterialID;
    private int listTopicID;
    private String materialName;
    private String topicName;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public int getListMaterialID() {
        return listMaterialID;
    }

    public void setListMaterialID(int listMaterialID) {
        this.listMaterialID = listMaterialID;
    }

    public int getListTopicID() {
        return listTopicID;
    }

    public void setListTopicID(int listTopicID) {
        this.listTopicID = listTopicID;
    }
}
