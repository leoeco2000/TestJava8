package effectiveJavaStudy.c34_enum;

public enum DayUseAbstractMethod {
	MONDAY {
		@Override
		void apply() {
			dealWithWeekDays();// α����
		}
	},
	TUESDAY {
		@Override
		void apply() {
			dealWithWeekDays();// α����
		}
	},
	WEDNESDAY {
		@Override
		void apply() {
			dealWithWeekDays();// α����
		}
	},
	THURSDAY {
		@Override
		void apply() {
			dealWithWeekDays();// α����
		}
	},
	FRIDAY {
		@Override
		void apply() {
			dealWithWeekDays();// α����
		}
	},
	SATURDAY {
		@Override
		void apply() {
			dealWithWeekEnds();// α����
		}
	},
	SUNDAY {
		@Override
		void apply() {
			dealWithWeekEnds();// α����
		}
	};

	abstract void apply();
	void dealWithWeekDays() {
	}
	void dealWithWeekEnds() {
	}
	
}
