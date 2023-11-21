def solution(rank, attendance):
    answer = 0
    prize = []
    dic = {}
    for i in range(len(rank)):
        dic[rank[i]] = attendance[i]
    
    for i in range(len(dic)):
        if dic[i+1] == True:
            prize.append(rank.index(i+1))
        if len(prize) == 3:
            break
    
    answer = 10000*prize[0] + 100*prize[1] + prize[2]
    
    return answer