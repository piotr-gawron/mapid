package list.mapid;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private String				id;
	private String				altGaId;
	private List<CogData>	cog		= new ArrayList<>();
	private List<String>	other	= new ArrayList<>();

	/**
	 * @return the id
	 * @see #id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *          the id to set
	 * @see #id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the altGaId
	 * @see #altGaId
	 */
	public String getAltGaId() {
		return altGaId;
	}

	/**
	 * @param altGaId
	 *          the altGaId to set
	 * @see #altGaId
	 */
	public void setAltGaId(String altGaId) {
		this.altGaId = altGaId;
	}

	/**
	 * @return the other
	 * @see #other
	 */
	public List<String> getOther() {
		return other;
	}

	/**
	 * @param other
	 *          the other to set
	 * @see #other
	 */
	public void setOther(List<String> other) {
		this.other = other;
	}

	/**
	 * @return the cog
	 * @see #cog
	 */
	public List<CogData> getCog() {
		return cog;
	}

	/**
	 * @param cog
	 *          the cog to set
	 * @see #cog
	 */
	public void setCog(List<CogData> cog) {
		this.cog = cog;
	}

}
