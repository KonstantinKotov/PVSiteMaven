package dao;


public class Section {

    private int sectionID;
    private String sectionName;
    private String sectionContext;
    private int sectionTopicID;
    private String sectionTopicName;

    public String getSectionTopicName() {
        return sectionTopicName;
    }

    public void setSectionTopicName(String sectionTopicName) {
        this.sectionTopicName = sectionTopicName;
    }

    public int getSectionTopicID() {
        return sectionTopicID;
    }

    public void setSectionTopicID(int sectionTopicID) {
        this.sectionTopicID = sectionTopicID;
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public String getSectionContext() {
        return sectionContext;
    }

    public void setSectionContext(String sectionContext) {
        this.sectionContext = sectionContext;
    }
}
