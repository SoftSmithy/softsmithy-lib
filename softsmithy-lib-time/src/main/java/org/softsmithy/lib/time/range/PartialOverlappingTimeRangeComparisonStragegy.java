/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.time.range;

import org.softsmithy.lib.time.TimeRange;

/**
 *
 * @author puce
 */
public enum PartialOverlappingTimeRangeComparisonStragegy {
    /**
     * (a, b) (x, y) a < x && x < b < y && b != y;
     */
    BEFORE_INCLUSIVE_ADJACENT {
        @Override
        public boolean isPartialOverlapping(TimeRange current, TimeRange other) {
            return other.getStartDateTime().isBefore(current.getStartDateTime())
                    && current.isInRange(other.getEndDateTime(), InTimeRangeComparisonStragegy.START_INCLUSIVE_END_EXCLUSIVE);
        }
    },
    /**
     * (a, b) (x, y) BEFORE_INCLUSIVE_ADJACENT && b != x;
     */
    BEFORE_EXLUSIVE_ADJACENT {
        @Override
        public boolean isPartialOverlapping(TimeRange current, TimeRange other) {
            return other.getStartDateTime().isBefore(current.getStartDateTime())
                    && current.isInRange(other.getEndDateTime(), InTimeRangeComparisonStragegy.START_EXCLUSIVE_END_EXCLUSIVE);
        }
    },
    AFTER_INCLUSIVE_ADJACENT {
        @Override
        public boolean isPartialOverlapping(TimeRange current, TimeRange other) {
            return other.getEndDateTime().isAfter(current.getEndDateTime())
                    && current.isInRange(other.getStartDateTime(), InTimeRangeComparisonStragegy.START_EXCLUSIVE_END_INCLUSVIE);
        }
    },
    AFTER_EXLUSIVE_ADJACENT {
        @Override
        public boolean isPartialOverlapping(TimeRange current, TimeRange other) {
            return other.getEndDateTime().isAfter(current.getEndDateTime())
                    && current.isInRange(other.getStartDateTime(), InTimeRangeComparisonStragegy.START_EXCLUSIVE_END_EXCLUSIVE);
        }
    },
    BEFORE_OR_AFTER_INCLUSIVE_ADJACENT {
        @Override
        public boolean isPartialOverlapping(TimeRange current, TimeRange other) {
            return BEFORE_INCLUSIVE_ADJACENT.isPartialOverlapping(current, other)
                    || AFTER_INCLUSIVE_ADJACENT.isPartialOverlapping(current, other);
        }
    },
    BEFORE_OR_AFTER_AFTER_EXLUSIVE_ADJACENT {
        @Override
        public boolean isPartialOverlapping(TimeRange current, TimeRange other) {
            return BEFORE_EXLUSIVE_ADJACENT.isPartialOverlapping(current, other)
                    || AFTER_EXLUSIVE_ADJACENT.isPartialOverlapping(current, other);
        }
    };

    public abstract boolean isPartialOverlapping(TimeRange current, TimeRange other);
}
