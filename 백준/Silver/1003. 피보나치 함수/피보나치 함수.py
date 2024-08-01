a=[];b=[]

def fibo(n):
    global a,b
    a.clear()
    b.clear()
    a.append(1)
    a.append(0)
    b.append(0)
    b.append(1)
    if n < 2:
        return
    else:
        for i in range(2,n+1):
            a.append(a[i-1]+a[i-2])
            b.append(b[i-1]+b[i-2])

n = int(input())
for i in range(n):
    q = int(input())
    temp = fibo(q)
    print(a[q],b[q])