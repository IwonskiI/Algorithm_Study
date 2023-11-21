def solution(arr):
    
    row = len(arr)
    col = len(arr[0])
    
    if row > col:
        for i in range(len(arr)):
            for _ in range(row - col):
                arr[i].append(0)
            
        
    elif row < col:
        for i in range(col - row):
            arr.append([0 for _ in range(col)])
    
    else:
        pass
    
    
    answer = arr
    return answer