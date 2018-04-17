# ECE519-ComputerProject
ECE 519 Information and Network Security Spring 2018 Computer Project

Our task is to implement a hash-based crypto puzzle using SHA-256. The way the crypto puzzle works is as follows. Alice issues a challenge to Bob: Alice creates a puzzle P that is B-bytes long, where B < 32. She challenges Bob to find a message M that, when fed into the SHA-256 function h() yields the last B bytes equal to P. That is, h(M) = [∗ ∗ ∗ · · · ∗ ∗∗, P]. Bob attempts to find such an M by generating random guesses and hashing them. When he finds one, he reports it to Alice and has solved the crypto puzzle.
