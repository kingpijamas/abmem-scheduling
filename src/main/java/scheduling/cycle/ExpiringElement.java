package scheduling.cycle;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;

public abstract class ExpiringElement extends CycleElement {

	private DateTime expiration;
	private ReadablePeriod lifetime;

	private ExpiringElement(DateTime expiration, ReadablePeriod term) {
		this.expiration = expiration;
		this.lifetime = term;
	}

	private ExpiringElement(Cycle cycle, DateTime expiration,
			ReadablePeriod term) {
		this(expiration, term);
		init(cycle);
	}

	public ExpiringElement(Cycle cycle, DateTime expiration) {
		this(cycle, expiration, new Period(cycle.getDate(), expiration));
	}

	public ExpiringElement(Cycle cycle, ReadablePeriod term) {
		this(cycle, cycle.getDate().plus(term), term);
	}

	public DateTime getExpiration() {
		return expiration;
	}

	public boolean isExpirationTime() {
		return getDate().equals(expiration);
	}

	protected ReadablePeriod getTerm() {
		return lifetime;
	}

	protected void extendExpiration() {
		expiration = expiration.plus(lifetime);
	}

	protected abstract void expire();
}
