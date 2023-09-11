def solution(intStrs, k, s, l):
    answer = []
    
    for i in range(len(intStrs)):
        number = int(intStrs[i][s:s+l])
        if number > k:
            answer.append(number)
    
    return answer

solution(["0123456789","9876543210","9999999999999"],50000,5,5)