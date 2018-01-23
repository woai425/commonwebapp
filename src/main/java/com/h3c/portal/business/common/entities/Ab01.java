package com.h3c.portal.business.common.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Ab01 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ab01",uniqueConstraints = @UniqueConstraint(columnNames = {
		"AAB001", "EAB014" }))
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ab01 implements java.io.Serializable {

	private static final long serialVersionUID = -5468915193625959253L;
	private Long aaz001;
	private String aab004;
	private String eab212;
	private String eab014;
	private String eab219;
	private String eab026;
	private String aab001;
	private String aab301;
	private String aaa027;
	private String aaf015;
	private String aab023;
	private String aaf020;
	private String aaa149;
	private String eab227;
	private String aab002;
	private String aab006;
	private String aab007;
	private Date aab008;
	private Short aab009;
	private String eab215;
	private String aab021;
	private String aab022;
	private String eab010;
	private Double eab012;
	private String aae013;
	private String aab003;
	private Timestamp eae278;
	private String aae004;
	private String eae279;
	private String eab231;
	private String eab230;
	private String aae006;
	private String aae007;
	private String eae284;
	private String aae159;
	private String aae045;
	private String eae280;
	private String eae281;
	private String eae283;
	private String aae046;
	private Timestamp aae047;
	private String aae048;
	private Timestamp aae049;
	private String aae051;
	private String eab213;
	private String eab221;
	private String eab222;
	private String eab223;
	private String eab224;
	private String eab225;
	private String eab226;
	private String eab228;
	private String eab229;
	private String aab030;
	private Integer version;
	private Timestamp abc;
	private Timestamp bcc;

	// Constructors

	/** default constructor */
	public Ab01() {
	}

	/** minimal constructor */
	public Ab01(Long aaz001, String aab004, String eab014, String eab219,
			String eab026, String aab001, String aab301, String aaa027,
			String aaf015, String aab023, String aaa149, String eab227,
			String aab006, String eab215, String aab021, String aab022,
			String aab003, Timestamp eae278, String eab221, String aab030) {
		this.aaz001 = aaz001;
		this.aab004 = aab004;
		this.eab014 = eab014;
		this.eab219 = eab219;
		this.eab026 = eab026;
		this.aab001 = aab001;
		this.aab301 = aab301;
		this.aaa027 = aaa027;
		this.aaf015 = aaf015;
		this.aab023 = aab023;
		this.aaa149 = aaa149;
		this.eab227 = eab227;
		this.aab006 = aab006;
		this.eab215 = eab215;
		this.aab021 = aab021;
		this.aab022 = aab022;
		this.aab003 = aab003;
		this.eae278 = eae278;
		this.eab221 = eab221;
		this.aab030 = aab030;
	}

	/** full constructor */
	public Ab01(Long aaz001, String aab004, String eab212, String eab014,
			String eab219, String eab026, String aab001, String aab301,
			String aaa027, String aaf015, String aab023, String aaf020,
			String aaa149, String eab227, String aab002, String aab006,
			String aab007, Date aab008, Short aab009, String eab215,
			String aab021, String aab022, String eab010, Double eab012,
			String aae013, String aab003, Timestamp eae278, String aae004,
			String eae279, String eab231, String eab230, String aae006,
			String aae007, String eae284, String aae159, String aae045,
			String eae280, String eae281, String eae283, String aae046,
			Timestamp aae047, String aae048, Timestamp aae049, String aae051,
			String eab213, String eab221, String eab222, String eab223,
			String eab224, String eab225, String eab226, String eab228,
			String eab229, String aab030, Integer version, Timestamp abc,
			Timestamp bcc) {
		this.aaz001 = aaz001;
		this.aab004 = aab004;
		this.eab212 = eab212;
		this.eab014 = eab014;
		this.eab219 = eab219;
		this.eab026 = eab026;
		this.aab001 = aab001;
		this.aab301 = aab301;
		this.aaa027 = aaa027;
		this.aaf015 = aaf015;
		this.aab023 = aab023;
		this.aaf020 = aaf020;
		this.aaa149 = aaa149;
		this.eab227 = eab227;
		this.aab002 = aab002;
		this.aab006 = aab006;
		this.aab007 = aab007;
		this.aab008 = aab008;
		this.aab009 = aab009;
		this.eab215 = eab215;
		this.aab021 = aab021;
		this.aab022 = aab022;
		this.eab010 = eab010;
		this.eab012 = eab012;
		this.aae013 = aae013;
		this.aab003 = aab003;
		this.eae278 = eae278;
		this.aae004 = aae004;
		this.eae279 = eae279;
		this.eab231 = eab231;
		this.eab230 = eab230;
		this.aae006 = aae006;
		this.aae007 = aae007;
		this.eae284 = eae284;
		this.aae159 = aae159;
		this.aae045 = aae045;
		this.eae280 = eae280;
		this.eae281 = eae281;
		this.eae283 = eae283;
		this.aae046 = aae046;
		this.aae047 = aae047;
		this.aae048 = aae048;
		this.aae049 = aae049;
		this.aae051 = aae051;
		this.eab213 = eab213;
		this.eab221 = eab221;
		this.eab222 = eab222;
		this.eab223 = eab223;
		this.eab224 = eab224;
		this.eab225 = eab225;
		this.eab226 = eab226;
		this.eab228 = eab228;
		this.eab229 = eab229;
		this.aab030 = aab030;
		this.version = version;
		this.abc = abc;
		this.bcc = bcc;
	}

	// Property accessors
	@Id
	@Column(name = "AAZ001", unique = true, nullable = false)
	public Long getAaz001() {
		return this.aaz001;
	}

	public void setAaz001(Long aaz001) {
		this.aaz001 = aaz001;
	}

	@Column(name = "AAB004", nullable = false, length = 200)
	public String getAab004() {
		return this.aab004;
	}

	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}

	@Column(name = "EAB212", length = 60)
	public String getEab212() {
		return this.eab212;
	}

	public void setEab212(String eab212) {
		this.eab212 = eab212;
	}

	@Column(name = "EAB014", nullable = false, length = 2)
	public String getEab014() {
		return this.eab014;
	}

	public void setEab014(String eab014) {
		this.eab014 = eab014;
	}

	@Column(name = "EAB219", nullable = false, length = 1)
	public String getEab219() {
		return this.eab219;
	}

	public void setEab219(String eab219) {
		this.eab219 = eab219;
	}

	@Column(name = "EAB026", nullable = false, length = 1)
	public String getEab026() {
		return this.eab026;
	}

	public void setEab026(String eab026) {
		this.eab026 = eab026;
	}

	@Column(name = "AAB001", nullable = false, length = 20)
	public String getAab001() {
		return this.aab001;
	}

	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}

	@Column(name = "AAB301", nullable = false, length = 6)
	public String getAab301() {
		return this.aab301;
	}

	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}

	@Column(name = "AAA027", nullable = false, length = 6)
	public String getAaa027() {
		return this.aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	@Column(name = "AAF015", nullable = false, length = 9)
	public String getAaf015() {
		return this.aaf015;
	}

	public void setAaf015(String aaf015) {
		this.aaf015 = aaf015;
	}

	@Column(name = "AAB023", nullable = false, length = 2)
	public String getAab023() {
		return this.aab023;
	}

	public void setAab023(String aab023) {
		this.aab023 = aab023;
	}

	@Column(name = "AAF020", length = 2)
	public String getAaf020() {
		return this.aaf020;
	}

	public void setAaf020(String aaf020) {
		this.aaf020 = aaf020;
	}

	@Column(name = "AAA149", nullable = false, length = 2)
	public String getAaa149() {
		return this.aaa149;
	}

	public void setAaa149(String aaa149) {
		this.aaa149 = aaa149;
	}

	@Column(name = "EAB227", nullable = false, length = 15)
	public String getEab227() {
		return this.eab227;
	}

	public void setEab227(String eab227) {
		this.eab227 = eab227;
	}

	@Column(name = "AAB002", length = 20)
	public String getAab002() {
		return this.aab002;
	}

	public void setAab002(String aab002) {
		this.aab002 = aab002;
	}

	@Column(name = "AAB006", nullable = false, length = 1)
	public String getAab006() {
		return this.aab006;
	}

	public void setAab006(String aab006) {
		this.aab006 = aab006;
	}

	@Column(name = "AAB007", length = 24)
	public String getAab007() {
		return this.aab007;
	}

	public void setAab007(String aab007) {
		this.aab007 = aab007;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AAB008", length = 10)
	public Date getAab008() {
		return this.aab008;
	}

	public void setAab008(Date aab008) {
		this.aab008 = aab008;
	}

	@Column(name = "AAB009")
	public Short getAab009() {
		return this.aab009;
	}

	public void setAab009(Short aab009) {
		this.aab009 = aab009;
	}

	@Column(name = "EAB215", nullable = false, length = 4)
	public String getEab215() {
		return this.eab215;
	}

	public void setEab215(String eab215) {
		this.eab215 = eab215;
	}

	@Column(name = "AAB021", nullable = false, length = 2)
	public String getAab021() {
		return this.aab021;
	}

	public void setAab021(String aab021) {
		this.aab021 = aab021;
	}

	@Column(name = "AAB022", nullable = false, length = 2)
	public String getAab022() {
		return this.aab022;
	}

	public void setAab022(String aab022) {
		this.aab022 = aab022;
	}

	@Column(name = "EAB010", length = 1)
	public String getEab010() {
		return this.eab010;
	}

	public void setEab010(String eab010) {
		this.eab010 = eab010;
	}

	@Column(name = "EAB012", precision = 22, scale = 0)
	public Double getEab012() {
		return this.eab012;
	}

	public void setEab012(Double eab012) {
		this.eab012 = eab012;
	}

	@Column(name = "AAE013", length = 100)
	public String getAae013() {
		return this.aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	@Column(name = "AAB003", nullable = false, length = 10)
	public String getAab003() {
		return this.aab003;
	}

	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}

	@Column(name = "EAE278", nullable = false, length = 19)
	public Timestamp getEae278() {
		return this.eae278;
	}

	public void setEae278(Timestamp eae278) {
		this.eae278 = eae278;
	}

	@Column(name = "AAE004", length = 50)
	public String getAae004() {
		return this.aae004;
	}

	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}

	@Column(name = "EAE279", length = 30)
	public String getEae279() {
		return this.eae279;
	}

	public void setEae279(String eae279) {
		this.eae279 = eae279;
	}

	@Column(name = "EAB231", length = 20)
	public String getEab231() {
		return this.eab231;
	}

	public void setEab231(String eab231) {
		this.eab231 = eab231;
	}

	@Column(name = "EAB230", length = 20)
	public String getEab230() {
		return this.eab230;
	}

	public void setEab230(String eab230) {
		this.eab230 = eab230;
	}

	@Column(name = "AAE006", length = 100)
	public String getAae006() {
		return this.aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	@Column(name = "AAE007", length = 6)
	public String getAae007() {
		return this.aae007;
	}

	public void setAae007(String aae007) {
		this.aae007 = aae007;
	}

	@Column(name = "EAE284", length = 80)
	public String getEae284() {
		return this.eae284;
	}

	public void setEae284(String eae284) {
		this.eae284 = eae284;
	}

	@Column(name = "AAE159", length = 50)
	public String getAae159() {
		return this.aae159;
	}

	public void setAae159(String aae159) {
		this.aae159 = aae159;
	}

	@Column(name = "AAE045", length = 50)
	public String getAae045() {
		return this.aae045;
	}

	public void setAae045(String aae045) {
		this.aae045 = aae045;
	}

	@Column(name = "EAE280", length = 40)
	public String getEae280() {
		return this.eae280;
	}

	public void setEae280(String eae280) {
		this.eae280 = eae280;
	}

	@Column(name = "EAE281", length = 50)
	public String getEae281() {
		return this.eae281;
	}

	public void setEae281(String eae281) {
		this.eae281 = eae281;
	}

	@Column(name = "EAE283", length = 64)
	public String getEae283() {
		return this.eae283;
	}

	public void setEae283(String eae283) {
		this.eae283 = eae283;
	}

	@Column(name = "AAE046", length = 18)
	public String getAae046() {
		return this.aae046;
	}

	public void setAae046(String aae046) {
		this.aae046 = aae046;
	}

	@Column(name = "AAE047", length = 19)
	public Timestamp getAae047() {
		return this.aae047;
	}

	public void setAae047(Timestamp aae047) {
		this.aae047 = aae047;
	}

	@Column(name = "AAE048", length = 100)
	public String getAae048() {
		return this.aae048;
	}

	public void setAae048(String aae048) {
		this.aae048 = aae048;
	}

	@Column(name = "AAE049", length = 19)
	public Timestamp getAae049() {
		return this.aae049;
	}

	public void setAae049(Timestamp aae049) {
		this.aae049 = aae049;
	}

	@Column(name = "AAE051", length = 100)
	public String getAae051() {
		return this.aae051;
	}

	public void setAae051(String aae051) {
		this.aae051 = aae051;
	}

	@Column(name = "EAB213", length = 9)
	public String getEab213() {
		return this.eab213;
	}

	public void setEab213(String eab213) {
		this.eab213 = eab213;
	}

	@Column(name = "EAB221", nullable = false, length = 1)
	public String getEab221() {
		return this.eab221;
	}

	public void setEab221(String eab221) {
		this.eab221 = eab221;
	}

	@Column(name = "EAB222", length = 1)
	public String getEab222() {
		return this.eab222;
	}

	public void setEab222(String eab222) {
		this.eab222 = eab222;
	}

	@Column(name = "EAB223", length = 20)
	public String getEab223() {
		return this.eab223;
	}

	public void setEab223(String eab223) {
		this.eab223 = eab223;
	}

	@Column(name = "EAB224", length = 20)
	public String getEab224() {
		return this.eab224;
	}

	public void setEab224(String eab224) {
		this.eab224 = eab224;
	}

	@Column(name = "EAB225", length = 1)
	public String getEab225() {
		return this.eab225;
	}

	public void setEab225(String eab225) {
		this.eab225 = eab225;
	}

	@Column(name = "EAB226", length = 2)
	public String getEab226() {
		return this.eab226;
	}

	public void setEab226(String eab226) {
		this.eab226 = eab226;
	}

	@Column(name = "EAB228", length = 20)
	public String getEab228() {
		return this.eab228;
	}

	public void setEab228(String eab228) {
		this.eab228 = eab228;
	}

	@Column(name = "EAB229", length = 20)
	public String getEab229() {
		return this.eab229;
	}

	public void setEab229(String eab229) {
		this.eab229 = eab229;
	}

	@Column(name = "AAB030", nullable = false, length = 40)
	public String getAab030() {
		return this.aab030;
	}

	public void setAab030(String aab030) {
		this.aab030 = aab030;
	}

	@Column(name = "VERSION")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "abc", length = 19)
	public Timestamp getAbc() {
		return this.abc;
	}

	public void setAbc(Timestamp abc) {
		this.abc = abc;
	}

	@Column(name = "bcc", length = 19)
	public Timestamp getBcc() {
		return this.bcc;
	}

	public void setBcc(Timestamp bcc) {
		this.bcc = bcc;
	}

}