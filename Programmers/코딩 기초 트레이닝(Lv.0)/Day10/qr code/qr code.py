def solution(q, r, code):
    answer = ''
    
    for i in range(len(code)):
        mod = i % q
        if mod == r:
            answer += code[i]
    
    return answer

solution(3,1,"qjnwezgrpirldywt")