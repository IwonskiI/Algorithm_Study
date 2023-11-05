def solution(binomial):
    answer = 0
    
    bi = binomial.split()
    if bi[1] == "+":
        answer = int(bi[0])+int(bi[2])
    elif bi[1] == "-":
        answer = int(bi[0])-int(bi[2])
    elif bi[1] == "*":
        answer = int(bi[0])*int(bi[2])
    
    return answer