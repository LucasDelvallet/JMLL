#!/usr/bin/env python
# an example implementation of Howcome for Python (debugging on program states), author: Andreas Zeller

import sys
import copy

def remove_html_markup(s):
    tag   = False
    quote = False
    out   = ""

    for c in s:
        # print c, tag, quote
        if c == '<' and not quote:
            tag = True
        elif c == '>' and not quote:
            tag = False
        elif c == '"' or c == "'" and tag:
            quote = not quote
        elif not tag:
            out = out + c

    return out
    
def ddmin(s):
    # assert test([]) == "PASS"
    assert test(s) == "FAIL"

    n = 2     # Initial granularity
    while len(s) >= 2:
        start = 0
        subset_length = len(s) / n
        some_complement_is_failing = False

        while start < len(s):
            complement = s[:start] + s[start + subset_length:]

            if test(complement) == "FAIL":
                s = complement
                n = max(n - 1, 2)
                some_complement_is_failing = True
                break
                
            start += subset_length

        if not some_complement_is_failing:
            if n == len(s):
                break
            n = min(n * 2, len(s))

    return s

# We use these variables to communicate between callbacks and drivers
the_line      = None
the_iteration = None
the_state     = None
the_diff      = None
the_input     = None

# Stop at THE_LINE/THE_ITERATION and store the state in THE_STATE
def trace_fetch_state(frame, event, arg):
    global the_line
    global the_iteration
    global the_state

    # print frame.f_lineno, frame.f_locals

    if frame.f_lineno == the_line:
        # print "Iteration", the_iteration, frame.f_locals
        the_iteration = the_iteration - 1
        if the_iteration == 0:
            the_state = copy.deepcopy(frame.f_locals)
            # print the_state
            the_line = None  # Don't get called again
            return None      # Don't get called again

    return trace_fetch_state

# Get the state at LINE/ITERATION
def get_state(input, line, iteration):
    global the_line
    global the_iteration
    global the_state
    
    the_line      = line
    the_iteration = iteration
    
    sys.settrace(trace_fetch_state)
    y = remove_html_markup(input)
    sys.settrace(None)
    
    return the_state

# Stop at THE_LINE/THE_ITERATION and apply the differences in THE_DIFF 
def trace_apply_diff(frame, event, arg):
    global the_line
    global the_diff
    global the_iteration
    
    # print frame.f_lineno, frame.f_locals

    if frame.f_lineno == the_line:
        # print "Iteration", the_iteration, frame.f_locals
        the_iteration = the_iteration - 1
        if the_iteration == 0:
            # for (var, value) in the_diff:
            #     print "Set", var, "to", repr(value)

            # Note: It is crucial to use update() here.  If we set elements one by one,
            # chances are that some changes will get lost.
            frame.f_locals.update(the_diff)
            
            # print frame.f_locals
            the_line = None
            return None  # Don't get called again
    
    return trace_apply_diff
    
# Testing function: Call remove_html_output, stop at THE_LINE/THE_ITERATION, 
# and apply the diffs in DIFFS at THE_LINE
def test(diffs):
    global the_diff
    global the_input
    global the_line
    global the_iteration
    
    # print "test(" + repr(diffs) + ") =",

    line      = the_line
    iteration = the_iteration
    
    the_diff = diffs
    sys.settrace(trace_apply_diff)
    y = remove_html_markup(the_input)
    sys.settrace(None)

    the_line      = line
    the_iteration = iteration

    # print repr(y),

    if y.find('<') == -1:
        # print "PASS"
        return "PASS"
    else:
        # print "FAIL"
        return "FAIL"
        
html_fail = '"<b>foo</b>"'
html_pass = "'<b>foo</b>'"

def test_it():
    global the_input, the_line, the_iteration

    # A bit of a test: Try to apply these differences in Line 8
    the_input = html_pass
    the_line      = 8
    the_iteration = 1
    assert test([('s', html_fail)]) == "FAIL"

    the_line      = 8
    the_iteration = 1
    assert test([('s', html_pass)]) == "PASS"

    the_line      = 12
    the_iteration = 1
    assert test([('quote', True)]) == "FAIL"

    the_line      = 12
    the_iteration = 1
    assert test([('quote', True), ('out', '')]) == "FAIL"
    assert test([('out', ''), ('quote', True)]) == "FAIL"

    the_line      = 14
    the_iteration = 3
    assert test([('quote', True), ('s', '"<b>foo</b>"'), ('tag', False), ('out', '<')]) == "FAIL"

# test_it()


print "The program was started with", repr(html_fail)

# Bigger tester over multiple locations
locations = [(8, 1), (12, 1), (14, 1), (14, 2), (14, 3), (23, 1)]
for (line, iteration) in locations:
    print "## Line", line, "/", iteration, "##"

    # Get the passing and the failing state
    state_pass = get_state(html_pass, line, iteration)
    state_fail = get_state(html_fail, line, iteration)

    # print "Passing state:", state_pass
    # print "Failing state:", state_fail

    # Compute the differences
    diffs = []
    for var in state_fail.keys():
        if not state_pass.has_key(var) or state_pass[var] != state_fail[var]:
            diffs.append((var, state_fail[var]))
    
    # print "State differences:", diffs
    
    # Minimize the failure-inducing set of differences
    the_input = html_pass
    the_line  = line
    the_iteration  = iteration
    cause = ddmin(diffs)
    
    # Pretty output
    print "Then, in Line " + repr(line) + " (iteration " + repr(iteration) + "),",
    for (var, value) in cause:
        print var, 'became', repr(value)
        
print "Then the program failed."

