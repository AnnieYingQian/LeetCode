class Solution {
  public int[] twoSum(int[] nums, int target) {
    // create return value
    int[] res = new int[2];

    // special case
    if (nums == null || nums.length <= 1) {
      return res;
    }
    
    // logic
    Map<Integer, Integer> remainSet = new HashMap<>(); // <remain, index>
    for (int i = 0; i < nums.length; i++) {
      if (remainSet.containsKey(nums[i])) {
        res[0] = remainSet.get(nums[i]);
        res[1] = i;
        return res;
      } else {
        remainSet.put(target - nums[i], i);
      }
    }
    return res;
  }
}
