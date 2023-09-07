def solution(n):
    answer = [[0 for _ in range(n)] for _ in range(n)]
    curX = 0
    curY = 0
    nxtX = [1,0,-1,0]
    nxtY = [0,1,0,-1]
    cnt = 1
    
    if n == 1:
        return [[1]]
    
    for i in range(2*n-1):
        while(answer[curY][curX]==0):
            answer[curY][curX] = cnt
            cnt += 1
            if curX + nxtX[i%4] >= 0 and curX + nxtX[i%4] < n:
                curX += nxtX[i%4]
            else:
                if i%2==0:
                    curY += nxtY[(i+1)%4]
                else:
                    curX += nxtX[(i+1)%4]
                break
            if curY + nxtY[i%4] >= 0 and curY + nxtY[i%4] < n:
                curY += nxtY[i%4]
            else:
                if i%2==0:
                    curY += nxtY[(i+1)%4]
                else:
                    curX += nxtX[(i+1)%4]
                break
            
        if answer[curY][curX] != 0:
            if i%2 != 0:
                curX += nxtX[(i+1)%4]
                curY += nxtY[(i+2)%4]
            else:
                curX += nxtX[(i+2)%4]
                curY += nxtY[(i+1)%4]
            
    return answer


n = 1
sol = solution(n)

for i in range(n):
    for j in range(n):
        s = '%4s' % str(sol[i][j])
        print(s, end=" ")
    print()