const validLength = (left, right, s) => {
  if (left >= right) {
    return 0
  }
  let leftCount = 0
  let rightCount = 0
  for (let i = left; i <= right; i++) {
    if (s[i] === '(') {
      leftCount += 1
    } else {
      rightCount += 1
    }
    if (rightCount > leftCount) {
      return 0
    }
  }
  return leftCount === rightCount ? leftCount + rightCount : 0
}
// // O(n^3) time
// const longestValidParentheses = (s = '') => {
//   let max = 0
//   for (let i = 0; i < s.length; i++) {
//     for (let j = i + 1; j < s.length; j++) {
//       max = Math.max(max, validLength(i, j, s))
//     }
//   }
//   return max
// }

// // dp O(n) time O(n) space
// const longestValidParentheses = (s = '') => {
//   let max = 0
//   const dp = new Array(s.length).fill(0)
//   for (let i = 1; i < s.length; i++) {
//     if (s[i] === ')' && s[i - 1] === '(') {
//       dp[i] = (dp[i - 2] || 0) + 2
//     } else if (s[i] === ')' && s[i - 1] === ')') {
//       if (s[i - 1 - dp[i - 1]] === '(') {
//         dp[i] = dp[i - 1] + 2 + (dp[i - dp[i - 1] - 2] || 0)
//       }
//     }
//     max = Math.max(dp[i], max)
//   }
//   return max
// }

// stack O(n) time O(n) space
const longestValidParentheses = (s = '') => {
  const stack = [-1]
  let max = 0
  for (let i = 0; i < s.length; i++) {
    if (s[stack[stack.length - 1]] === '(' && s[i] === ')') {
      stack.pop()
    } else {
      max = Math.max(max, i - stack[stack.length - 1] - 1)
      stack.push(i)
    }
  }

  return Math.max(max, s.length - stack[stack.length - 1] - 1)
}

// This solution uses a stack to keep track of the indices of characters that are part of an invalid sequence.
// The initial -1 in the stack acts as a sentinel value, providing a base for calculating lengths.
// When a mismatch occurs, we calculate the length of the valid sequence that just ended and push the current index as a new invalid boundary.
// The final return statement handles cases where the longest valid substring extends to the end of the input string.
