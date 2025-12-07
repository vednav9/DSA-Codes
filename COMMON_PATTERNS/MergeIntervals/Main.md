# CORE IDEA OF INTERVALS PATTERN

Intervals are always in the form:

    [start, end]


The key idea behind all problems:

✔ First: Sort intervals by start time
✔ Then: compare current interval with previous merged interval
✔ If overlapping → merge
✔ If not → add as new interval

Almost ALL interval problems reduce to this.