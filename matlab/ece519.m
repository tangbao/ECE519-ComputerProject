clear;clc;

B = [
1
2
3
4
];

ave_trials = [
253.898
69080.71
16887421.35
4069363775
];

ave_time = [
0.015
0.114
20.601
5255.7448
];

two2n = [
256
65536
16777216
4294967296
];



figure(1)
plot(ave_trials, two2n, '*-');
xlabel('2^n, n = 8, 16, 24, 32');
ylabel('Average Number of Trials');
title('Average Number of Trials and 2^n');
grid on;

figure(2)
plot(B, ave_trials, '*-');
xlabel('Size of B (Bytes)');
ylabel('Average Number of Trials');
title('Size of B - Average Number of trials');
grid on;

figure(3)
plot(B, ave_time, '*-');
xlabel('Size of B (Bytes)');
ylabel('Average Time (s)');
title('Size of B and Average Time (s)');
grid on;

figure(4)
plot(ave_time, ave_trials, '*-');
xlabel('Average Time (s)');
ylabel('Average Number of Trials');
title('Average Time and Average Number of Trials');
grid on;
