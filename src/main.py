import sys 

def getTroco(valor:int, moedasDisp:list[int]) -> dict:
    resp = dict()
    moedasDisp.sort()
    moedasDisp.reverse()
    for moeda in moedasDisp:
        aux  = int( valor / moeda)
        valor = valor % moeda
        resp[moeda] = aux

    return resp

def sdm_guloso(s:list[int], f:list[int]):
    x = []
    i = 0

    for k in range(1,len(f)):
        print("k: ", k)
        print("i: ", i)

        if s[k] > f[i]:
            x.append(k)
            i = k
    return x


def main():

    s = [4,6,13,4,2,6,7,9,1,3,9]
    s.sort()
    f = [8, 7, 14, 5, 4,9,10,11,6,13,12]
    f.sort()

    print(sdm_guloso(s, f))

   

if __name__ == '__main__':
     
    print(getTroco(int(sys.argv[-1]), [1,10,50,25,100]))    