import java.util.Date;

/*
 * Program contents
 */


public class ProgramDto {

	
	public String getChannel_name() {
		return channel_name;
	}
	public String getPre_name() {
		return pre_name;
	}
	public String getPlay_date() {
		return play_date;
	}
	public String getStrat_time() {
		return strat_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public Integer getsaved_days() {
		return saved_days;
	}
	public Integer getAllow_record() {
		return allow_record;
	}
	public Integer getTVOD_type() {
		return TVOD_type;
	}
	public Integer getTVOD_unit() {
		return TVOD_unit;
	}
	public Integer getTVOD_price() {
		return TVOD_price;
	}
	public Integer getAllow_personal() {
		return allow_personal;
	}
	public Integer getPersonal_record_type() {
		return personal_record_type;
	}
	public Integer getPersonal_price_unit() {
		return personal_price_unit;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public void setPre_name(String pre_name) {
		this.pre_name = pre_name;
	}
	public void setPlay_date(String play_date) {
		this.play_date = play_date;
	}
	public void setStrat_time(String strat_time) {
		this.strat_time = strat_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public void setsaved_days(Integer saved_days) {
		this.saved_days = saved_days;
	}
	public void setAllow_record(Integer allow_record) {
		this.allow_record = allow_record;
	}
	public void setTVOD_type(Integer tVOD_type) {
		TVOD_type = tVOD_type;
	}
	public void setTVOD_unit(Integer tVOD_unit) {
		TVOD_unit = tVOD_unit;
	}
	public void setTVOD_price(Integer tVOD_price) {
		TVOD_price = tVOD_price;
	}
	public void setAllow_personal(Integer allow_personal) {
		this.allow_personal = allow_personal;
	}
	public void setPersonal_record_type(Integer personal_record_type) {
		this.personal_record_type = personal_record_type;
	}
	public void setPersonal_price_unit(Integer personal_price_unit) {
		this.personal_price_unit = personal_price_unit;
	}
	public void setPersonal_record_price(Integer personal_record_price) {
		this.personal_record_price = personal_record_price;
	}
	public void setPre_description(String pre_description) {
		this.pre_description = pre_description;
	}
	public void setAuto_code(Integer auto_code) {
		this.auto_code = auto_code;
	}
	public Integer getPersonal_record_price() {
		return personal_record_price;
	}
	public String getPre_description() {
		return pre_description;
	}
	public Integer getAuto_code() {
		return auto_code;
	}
	
	private String channel_name;
	private String pre_name;
	private String play_date;
	private String strat_time;
	private String end_time;
	private Integer saved_days;
	private Integer allow_record;
	private Integer TVOD_type;
	private Integer TVOD_unit;
	private Integer TVOD_price;
	private Integer allow_personal;
	private Integer personal_record_type;
	private Integer personal_price_unit;
	private Integer personal_record_price;
	private String pre_description;
	private Integer auto_code;
	
	
	
}
