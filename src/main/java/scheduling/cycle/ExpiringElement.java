package scheduling.cycle;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;

public abstract class ExpiringElement extends CycleElement {

	private DateTime expiration;
	private ReadablePeriod term;

	private ExpiringElement(Cycle circuit, DateTime expiration,
			ReadablePeriod term) {
		super(circuit);
		this.expiration = expiration;
		this.term = term;
	}

	public ExpiringElement(Cycle circuit, DateTime expiration) {
		this(circuit, expiration, new Period(circuit.getDate(), expiration));
	}

	public ExpiringElement(Cycle circuit, ReadablePeriod term) {
		this(circuit, circuit.getDate().plus(term), term);
	}

	public DateTime getExpiration() {
		return expiration;
	}

	public boolean isExpirationTime() {
		return getDate().equals(expiration);
	}

	protected ReadablePeriod getTerm() {
		return term;
	}

	protected void extendExpiration() {
		expiration = expiration.plus(term);
	}

	protected abstract void expire();
}
