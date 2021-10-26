
# HasMap
1.     First the hashmap is created to map the time of the match which is per_match, First_half, second_half, full_time, half_etc.

# ArrayList
2.     Then the method is created to read the data from the text file which is where the data is supplied from. In the method readTextFile() the data is read from file and put into a ArrayList. This method is called form the main method.

# Fomat method
3.     In the main method, now for each is used to pick each value of the arrayList and the method getFormatted() is called.

4.     The getFormatted() is the method in which all the major functionality takes place.
5.     
# String Formatting and conditional passing
a. First, the period is captured as it’s the part of the substring.

b. Then the regular expression is taken to check if the input values correctly is in the format (PM|H1|HT|H2|FT)\\]\\s+\\d{1,2}:\\d{1,2}\\.\\d{1,3}. If not then invalid value is returned.

c. Then the value is split on space to and the length of the values is checked to be equal to 2, if not then Invalid is returned.

d. The period is taken from the first part of the split(split[0]) and replaced with uppercase and using the hashmap created above the values of time to period are pulled. If the values is null, then invalid is returned.

e. If the values from the second part of the split(split[1]) contains “–“ then invalid is returned.

f. Then the second part of the split(split[1]) is again split on “.” to get the milli seconds and then parsed to integer.

g. Then the first part of the split value from step f is which contains only mins and seconds are split again again on “:” to get the minutes and seconds. Those values are also parsed to integer.

h. The millisecond is checked if its more than 500, if yes then 1 is added to seconds and milliseconds become 0.

i. Then the minute and seconds are separately  is checked if it is less than 45 and if yes, then the values is made to be displayed in the format which is needed using another method prefix().

j. In another condition, the values are checked to be more than 45 and less than 90 and having period as H1 then the additional time is considered. The additional time is calculated by subtracting minute with 45.

k. Similarly, the values are checked to be more than 45 and less than 90 and having period as HT then the its taken as half time.

l. Again, the values are checked to less than 90 and having period as H2 then the its taken as second_half. The additional time is calculated by subtracting minute with 90.

5.     The prefix() method checks the values if its is more than 10, if yes, then just the values is taken, if not then its concatenated with 0. This values is converted to string and returned.
