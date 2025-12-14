# FAST & SLOW POINTER — CORE IDEA

You have two pointers:
- slow → moves 1 step
- fast → moves 2 steps

If there is a cycle:
- fast eventually “laps” slow → they meet - If there is no cycle:
- fast reaches null → no cycle

This works because moving at different speeds creates a guaranteed meeting point inside a cycle.