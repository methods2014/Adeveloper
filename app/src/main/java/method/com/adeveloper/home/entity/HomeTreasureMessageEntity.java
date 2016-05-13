package method.com.adeveloper.home.entity;

/**
 * Created by wangs on 16/2/19 11:17.
 */
public class HomeTreasureMessageEntity extends BaseAdvertiseEntity{
    //    "channel": "11",
//            "clickTag": "JDdbd_201601312|6",
//            "dbdSpecTag": "疯抢",
//            "dbdSpecTitle": "纸尿裤囤货一元起抢！",
//            "dbdSpecUrl": "http://storage.jd.com/ershou.paipai.com/cdn/32031436adcbc875/files/idc-201.shtml",
//            "dbdSpecslogin": "纸尿裤 1元抢+京配包邮+发票",
//            "endTime": "2016-02-29 15:00:00",
//            "osPlant": "0",
//            "ppms_itemName": "头条1",
//            "startTime": "2016-02-15 14:00:00",
//            "type": "1",
//            "versionCode": "11"
//    private String channel;
    private String clickTag;
    private String dbdSpecTag;
    private String dbdSpecTitle;
    private String dbdSpecUrl;
    private String dbdSpecslogin;
    private String endTime;
//    private String osPlant;
    private String ppms_itemName;
    private String startTime;
    private String type;
//    private String versionCode;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getClickTag() {
        return clickTag;
    }

    public void setClickTag(String clickTag) {
        this.clickTag = clickTag;
    }

    public String getDbdSpecTag() {
        return dbdSpecTag;
    }

    public void setDbdSpecTag(String dbdSpecTag) {
        this.dbdSpecTag = dbdSpecTag;
    }

    public String getDbdSpecTitle() {
        return dbdSpecTitle;
    }

    public void setDbdSpecTitle(String dbdSpecTitle) {
        this.dbdSpecTitle = dbdSpecTitle;
    }

    public String getDbdSpecUrl() {
        return dbdSpecUrl;
    }

    public void setDbdSpecUrl(String dbdSpecUrl) {
        this.dbdSpecUrl = dbdSpecUrl;
    }

    public String getDbdSpecslogin() {
        return dbdSpecslogin;
    }

    public void setDbdSpecslogin(String dbdSpecslogin) {
        this.dbdSpecslogin = dbdSpecslogin;
    }

    public String getOsPlant() {
        return osPlant;
    }

    public void setOsPlant(String osPlant) {
        this.osPlant = osPlant;
    }

    public String getPpms_itemName() {
        return ppms_itemName;
    }

    public void setPpms_itemName(String ppms_itemName) {
        this.ppms_itemName = ppms_itemName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {

        this.versionCode = versionCode;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeTreasureMessageEntity)) return false;

        HomeTreasureMessageEntity that = (HomeTreasureMessageEntity) o;

        if (channel != null ? !channel.equals(that.channel) : that.channel != null) return false;
        if (clickTag != null ? !clickTag.equals(that.clickTag) : that.clickTag != null)
            return false;
        if (dbdSpecTag != null ? !dbdSpecTag.equals(that.dbdSpecTag) : that.dbdSpecTag != null)
            return false;
        if (dbdSpecTitle != null ? !dbdSpecTitle.equals(that.dbdSpecTitle) : that.dbdSpecTitle != null)
            return false;
        if (dbdSpecUrl != null ? !dbdSpecUrl.equals(that.dbdSpecUrl) : that.dbdSpecUrl != null)
            return false;
        if (dbdSpecslogin != null ? !dbdSpecslogin.equals(that.dbdSpecslogin) : that.dbdSpecslogin != null)
            return false;
        if (osPlant != null ? !osPlant.equals(that.osPlant) : that.osPlant != null) return false;
        if (ppms_itemName != null ? !ppms_itemName.equals(that.ppms_itemName) : that.ppms_itemName != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return versionCode != null ? versionCode.equals(that.versionCode) : that.versionCode == null;

    }

    @Override
    public int hashCode() {
        int result = channel != null ? channel.hashCode() : 0;
        result = 31 * result + (clickTag != null ? clickTag.hashCode() : 0);
        result = 31 * result + (dbdSpecTag != null ? dbdSpecTag.hashCode() : 0);
        result = 31 * result + (dbdSpecTitle != null ? dbdSpecTitle.hashCode() : 0);
        result = 31 * result + (dbdSpecUrl != null ? dbdSpecUrl.hashCode() : 0);
        result = 31 * result + (dbdSpecslogin != null ? dbdSpecslogin.hashCode() : 0);
        result = 31 * result + (osPlant != null ? osPlant.hashCode() : 0);
        result = 31 * result + (ppms_itemName != null ? ppms_itemName.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (versionCode != null ? versionCode.hashCode() : 0);
        return result;
    }
}
