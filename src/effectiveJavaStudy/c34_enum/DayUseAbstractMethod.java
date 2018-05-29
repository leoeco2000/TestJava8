package effectiveJavaStudy.c34_enum;

public enum DayUseAbstractMethod {
	MONDAY {
		@Override
		void apply() {
			dealWithWeekDays();// 伪代码
		}
	},
	TUESDAY {
		@Override
		void apply() {
			dealWithWeekDays();// 伪代码
		}
	},
	WEDNESDAY {
		@Override
		void apply() {
			dealWithWeekDays();// 伪代码
		}
	},
	THURSDAY {
		@Override
		void apply() {
			dealWithWeekDays();// 伪代码
		}
	},
	FRIDAY {
		@Override
		void apply() {
			dealWithWeekDays();// 伪代码
		}
	},
	SATURDAY {
		@Override
		void apply() {
			dealWithWeekEnds();// 伪代码
		}
	},
	SUNDAY {
		@Override
		void apply() {
			dealWithWeekEnds();// 伪代码
		}
	};

	abstract void apply();
	void dealWithWeekDays() {
	}
	void dealWithWeekEnds() {
	}
	
}
