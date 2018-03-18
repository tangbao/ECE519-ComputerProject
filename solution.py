from hashlib import sha256
from multiprocessing import Pool, cpu_count
from os import urandom


def solution(target: str):
    def callback(message: str):
        print('message: ', message)
        print('digest: ', sha256(message).hexdigest())
        p.terminate()

    if len(target) % 2 == 1 or len(target) / 2 >= 32:
        raise ValueError

    p = Pool(processes=cpu_count() * 2)

    for _ in range(cpu_count() * 2):
        p.apply_async(find, (target,), callback=callback)
    p.close()
    p.join()


def find(target: str):
    length = len(target)
    s = b''
    while True:
        s = urandom(56)
        if sha256(s).hexdigest()[-length:] == target:
            return s


if __name__ == '__main__':
    solution('117ae3')
