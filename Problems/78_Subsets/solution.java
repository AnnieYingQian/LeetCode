class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums == null) {
            return results;
        }

        if (nums.length == 0) {
            results.add(new ArrayList<>());
            return results;
        }

        Arrays.sort(nums);
        subsetsHelper(new ArrayList<>(), nums, 0, results);
        return results;
    }

    private void subsetsHelper(ArrayList<Integer> subset,
                               int[] nums,
                               int start,
                               List<List<Integer>> results) {
        results.add(new ArrayList<Integer>(subset));

        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            subsetsHelper(subset, nums, i + 1, results);
            subset.remove(subset.size() - 1);
        }
    }
}
