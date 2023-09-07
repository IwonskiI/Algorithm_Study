def solution(code):
    answer = ''
    mode = 0
    
    for i in range(len(code)):
        if code[i] != '1':
            if mode == 0:
                if i%2 == 0:
                    answer += code[i]
            else:
                if i%2 != 0:
                    answer += code[i]
        else:
            if mode == 1:
                mode = 0
            else:
                mode = 1
    
    if answer == '':
        answer = "EMPTY"
    
    return answer