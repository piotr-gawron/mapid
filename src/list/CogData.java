package list;

public class CogData {
	private String cogId;
	private String cogLetter;

	public CogData(String cogId2) {
		this.cogId = cogId2;
	}

	/**
	 * @return the cogId
	 * @see #cogId
	 */
	public String getCogId() {
		return cogId;
	}

	/**
	 * @param cogId
	 *          the cogId to set
	 * @see #cogId
	 */
	public void setCogId(String cogId) {
		this.cogId = cogId;
	}

	/**
	 * @return the cogLetter
	 * @see #cogLetter
	 */
	public String getCogLetter() {
		return cogLetter;
	}

	/**
	 * @param cogLetter
	 *          the cogLetter to set
	 * @see #cogLetter
	 */
	public void setCogLetter(String cogLetter) {
		this.cogLetter = cogLetter;
	}
}
