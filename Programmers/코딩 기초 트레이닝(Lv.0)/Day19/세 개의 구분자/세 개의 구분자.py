def solution(myStr):
    answer = []

    myStr = (' ').join(myStr.split('a'))
    myStr = (' ').join(myStr.split('b'))
    myStr = (' ').join(myStr.split('c'))
    myStr = myStr.split()
    
    answer = myStr
    
    if len(answer) == 0:
        answer = ["EMPTY"]
    
    return answer