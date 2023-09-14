def solution(arr):
    answer = []
    srt = -1
    end = -1
    for i in range(len(arr)):
        if arr[i] == 2:
            srt = i
            break
    for i in range(len(arr)-1,-1,-1):
        if arr[i] == 2:
            end = i
            break

    if srt != -1 and end != -1:
        for i in range(srt,end+1):
            answer.append(arr[i])
    else:
        answer.append(-1)
    return answer