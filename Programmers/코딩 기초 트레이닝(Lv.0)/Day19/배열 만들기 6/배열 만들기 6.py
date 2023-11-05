def solution(arr):
    stk = []
    i = 0
    
    while(len(arr)>i):
        if len(stk) == 0:
            stk.append(arr[i])
            i += 1
        else:
            if stk[-1] == arr[i]:
                stk.pop()
                i += 1
            else:
                stk.append(arr[i])
                i += 1
    if len(stk) == 0:
        stk = [-1]

    return stk