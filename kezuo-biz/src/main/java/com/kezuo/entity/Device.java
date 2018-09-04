package com.kezuo.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 机井设备
 * </p>
 *
 * @author zlzuo
 * @since 2018-08-27
 */
public class Device extends Model<Device> {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    private String SimNo;
    /**
     * 机井设备名称
     */
    private String DeviceName;
    /**
     * 描述
     */
    private String Description;
    /**
     * 安装时间
     */
    private Date SetupDate;
    /**
     * 安装地点
     */
    private String SetupAddress;
    /**
     * 经度_Baidu
     */
    @TableField("LON")
    private Long lon;
    /**
     * 纬度_Baidu
     */
    @TableField("LAT")
    private Long lat;
    /**
     * 当前设备是否有效,当移除设备后，当前设备无效，不参与计算
     */
    private Integer IsValid;
    /**
     * 最后修改时间
     */
    private Date LastUpdate;
    /**
     * 所属行政区域
     */
    private Long DistrictId;
    /**
     * 设备序号
     */
    private String DeviceNo;
    /**
     * 0:离线，1:在线
     */
    private Integer Online;
    /**
     * 最后在线时间
     */
    private Date OnlineTime;
    /**
     * 年可开采水量
     */
    private String YearExploitation;
    /**
     * 可用水量提醒
     */
    private Integer AlertAvailableWater;
    /**
     * 可用水量提醒
     */
    private Integer AlertAvailableElectric;
    /**
     * 流量计类型Id
     */
    private Integer DeviceTypeCodeId;
    /**
     * 电表脉冲数
     */
    private Integer MeterPulse;
    /**
     * 水位报警值
     */
    private String AlertWaterLevel;
    /**
     * 设备状态
     */
    private String TerminalState;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 作物Id
     */
    private Long CropId;
    /**
     * 地块面积
     */
    private BigDecimal Area;
    /**
     * 站类型，0-单站，01-主站，02-从站
     */
    private Integer StationType;
    /**
     * 地址码，主站地址码为0，从站地址码1-32
     */
    private Integer StationCode;
    /**
     * 通信频率00-1F
     */
    private Integer Frequency;
    /**
     * 主站主键Id
     */
    private Long MainId;
    /**
     * 设备类型，水泵、水位仪、气象仪、墒情仪
     */
    private String DeviceType;
    @TableField("Rainfall_Hour")
    private BigDecimal rainfallHour;
    private BigDecimal WaterLevel;
    @TableId(value = "Id", type = IdType.AUTO)
    private Long Id;
    private BigDecimal WaterUsed;
    @TableField("Rainfall_Total")
    private BigDecimal rainfallTotal;
    private BigDecimal Rainfall;
    private String RemoteStation;
    @TableField("Acq_Time")
    private Date acqTime;
    @TableField("Rainfall_Day")
    private BigDecimal rainfallDay;
    
    private String DistrictCode;

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String DistrictCode) {
        this.DistrictCode = DistrictCode;
    }


    public String getSimNo() {
        return SimNo;
    }

    public void setSimNo(String SimNo) {
        this.SimNo = SimNo;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String DeviceName) {
        this.DeviceName = DeviceName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getSetupDate() {
        return SetupDate;
    }

    public void setSetupDate(Date SetupDate) {
        this.SetupDate = SetupDate;
    }

    public String getSetupAddress() {
        return SetupAddress;
    }

    public void setSetupAddress(String SetupAddress) {
        this.SetupAddress = SetupAddress;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Integer getIsValid() {
        return IsValid;
    }

    public void setIsValid(Integer IsValid) {
        this.IsValid = IsValid;
    }

    public Date getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(Date LastUpdate) {
        this.LastUpdate = LastUpdate;
    }

    public Long getDistrictId() {
        return DistrictId;
    }

    public void setDistrictId(Long DistrictId) {
        this.DistrictId = DistrictId;
    }

    public String getDeviceNo() {
        return DeviceNo;
    }

    public void setDeviceNo(String DeviceNo) {
        this.DeviceNo = DeviceNo;
    }

    public Integer getOnline() {
        return Online;
    }

    public void setOnline(Integer Online) {
        this.Online = Online;
    }

    public Date getOnlineTime() {
        return OnlineTime;
    }

    public void setOnlineTime(Date OnlineTime) {
        this.OnlineTime = OnlineTime;
    }

    public String getYearExploitation() {
        return YearExploitation;
    }

    public void setYearExploitation(String YearExploitation) {
        this.YearExploitation = YearExploitation;
    }

    public Integer getAlertAvailableWater() {
        return AlertAvailableWater;
    }

    public void setAlertAvailableWater(Integer AlertAvailableWater) {
        this.AlertAvailableWater = AlertAvailableWater;
    }

    public Integer getAlertAvailableElectric() {
        return AlertAvailableElectric;
    }

    public void setAlertAvailableElectric(Integer AlertAvailableElectric) {
        this.AlertAvailableElectric = AlertAvailableElectric;
    }

    public Integer getDeviceTypeCodeId() {
        return DeviceTypeCodeId;
    }

    public void setDeviceTypeCodeId(Integer DeviceTypeCodeId) {
        this.DeviceTypeCodeId = DeviceTypeCodeId;
    }

    public Integer getMeterPulse() {
        return MeterPulse;
    }

    public void setMeterPulse(Integer MeterPulse) {
        this.MeterPulse = MeterPulse;
    }

    public String getAlertWaterLevel() {
        return AlertWaterLevel;
    }

    public void setAlertWaterLevel(String AlertWaterLevel) {
        this.AlertWaterLevel = AlertWaterLevel;
    }

    public String getTerminalState() {
        return TerminalState;
    }

    public void setTerminalState(String TerminalState) {
        this.TerminalState = TerminalState;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public Long getCropId() {
        return CropId;
    }

    public void setCropId(Long CropId) {
        this.CropId = CropId;
    }

    public BigDecimal getArea() {
        return Area;
    }

    public void setArea(BigDecimal Area) {
        this.Area = Area;
    }

    public Integer getStationType() {
        return StationType;
    }

    public void setStationType(Integer StationType) {
        this.StationType = StationType;
    }

    public Integer getStationCode() {
        return StationCode;
    }

    public void setStationCode(Integer StationCode) {
        this.StationCode = StationCode;
    }

    public Integer getFrequency() {
        return Frequency;
    }

    public void setFrequency(Integer Frequency) {
        this.Frequency = Frequency;
    }

    public Long getMainId() {
        return MainId;
    }

    public void setMainId(Long MainId) {
        this.MainId = MainId;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String DeviceType) {
        this.DeviceType = DeviceType;
    }

    public BigDecimal getRainfallHour() {
        return rainfallHour;
    }

    public void setRainfallHour(BigDecimal rainfallHour) {
        this.rainfallHour = rainfallHour;
    }

    public BigDecimal getWaterLevel() {
        return WaterLevel;
    }

    public void setWaterLevel(BigDecimal WaterLevel) {
        this.WaterLevel = WaterLevel;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public BigDecimal getWaterUsed() {
        return WaterUsed;
    }

    public void setWaterUsed(BigDecimal WaterUsed) {
        this.WaterUsed = WaterUsed;
    }

    public BigDecimal getRainfallTotal() {
        return rainfallTotal;
    }

    public void setRainfallTotal(BigDecimal rainfallTotal) {
        this.rainfallTotal = rainfallTotal;
    }

    public BigDecimal getRainfall() {
        return Rainfall;
    }

    public void setRainfall(BigDecimal Rainfall) {
        this.Rainfall = Rainfall;
    }

    public String getRemoteStation() {
        return RemoteStation;
    }

    public void setRemoteStation(String RemoteStation) {
        this.RemoteStation = RemoteStation;
    }

    public Date getAcqTime() {
        return acqTime;
    }

    public void setAcqTime(Date acqTime) {
        this.acqTime = acqTime;
    }

    public BigDecimal getRainfallDay() {
        return rainfallDay;
    }

    public void setRainfallDay(BigDecimal rainfallDay) {
        this.rainfallDay = rainfallDay;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "Device{" +
        ", SimNo=" + SimNo +
        ", DeviceName=" + DeviceName +
        ", Description=" + Description +
        ", SetupDate=" + SetupDate +
        ", SetupAddress=" + SetupAddress +
        ", lon=" + lon +
        ", lat=" + lat +
        ", IsValid=" + IsValid +
        ", LastUpdate=" + LastUpdate +
        ", DistrictId=" + DistrictId +
        ", DeviceNo=" + DeviceNo +
        ", Online=" + Online +
        ", OnlineTime=" + OnlineTime +
        ", YearExploitation=" + YearExploitation +
        ", AlertAvailableWater=" + AlertAvailableWater +
        ", AlertAvailableElectric=" + AlertAvailableElectric +
        ", DeviceTypeCodeId=" + DeviceTypeCodeId +
        ", MeterPulse=" + MeterPulse +
        ", AlertWaterLevel=" + AlertWaterLevel +
        ", TerminalState=" + TerminalState +
        ", Remark=" + Remark +
        ", CropId=" + CropId +
        ", Area=" + Area +
        ", StationType=" + StationType +
        ", StationCode=" + StationCode +
        ", Frequency=" + Frequency +
        ", MainId=" + MainId +
        ", DeviceType=" + DeviceType +
        ", rainfallHour=" + rainfallHour +
        ", WaterLevel=" + WaterLevel +
        ", Id=" + Id +
        ", WaterUsed=" + WaterUsed +
        ", rainfallTotal=" + rainfallTotal +
        ", Rainfall=" + Rainfall +
        ", RemoteStation=" + RemoteStation +
        ", acqTime=" + acqTime +
        ", rainfallDay=" + rainfallDay +
        "}";
    }
}
