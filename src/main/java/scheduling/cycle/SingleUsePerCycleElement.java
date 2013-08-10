package scheduling.cycle;

import org.joda.time.DateTime;

import scheduling.cycle.exceptions.UnexpectedInvocationException;

public abstract class SingleUsePerCycleElement extends CycleElement {

	private DateTime lastUse;

	public SingleUsePerCycleElement() {
		this.lastUse = null;
	}

	public SingleUsePerCycleElement(Cycle cycle) {
		super(cycle);
		this.lastUse = null;
	}

	public void use() {
		if (!isUseable()) {
			throw new UnexpectedInvocationException(
					"Already used in the current period.");
		}
		lastUse = getDate();
	}

	public DateTime getLastUse() {
		return lastUse;
	}

	public boolean isUseable() {
		return lastUse == null || lastUse.compareTo(getDate()) < 0;
	}
}
