import hashlib, random, string


def random_str(randomlength=8):
    a = list(string.ascii_letters)
    random.shuffle(a)
    return ''.join(a[:randomlength])

P = 'fda4'
while True:
    M = random_str(8)
    hash = hashlib.sha256()
    hash.update(M.encode('utf-8'))
    print(hash.hexdigest())
    if (hash.hexdigest()[-len(P):] == P):
        print(M)
        break
