#include <bits/stdc++.h>
using namespace std;

int main()
{
    int t,v;
    string in;
    cin >> t;
    while(t--){
        getline(cin,in);
        string issi(in);
        vector<int> numero;
        int sum = 0;
        while(issi >> v){
            sum += v;
            numero.push_back(v);
        }
        if(sum%2) cout << "NO\n";
        else {
            sum/=2;
            vector<bool> disc(sum+1);
            disc[0] = true;
            for(auto& num : numero){
                for(int i=sum;i>=num;i--)
                    disc[i] = dp[i-num] ? true : dp[i];
            }
            if(disc[sum]) cout << "YES\n";
            else cout << "NO\n";
        }
    }
}