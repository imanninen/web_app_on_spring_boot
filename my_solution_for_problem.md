# My solution for the internship problem
I think, main approach is to test all commits asynchronous. Why we can use asynchronous method? Usually, tests are independant, so, we could split testing into parts,
and mark every part as `suspend`. So, now we could execute all test asynchronous.
So, I think we should write something like this:
```
val allJobs: MutableList<Job>
val coroutineDispatcher = Dispatchers.IO.limitedParallelism(numberOfCoroutines)
val scope = CoroutineScope(coroutineDispatcher + SupervisorJob() +
            CoroutineExceptionHandler { _, error ->
                println("error: ${error.message}")
            })

// Then in some cycle we check for new Jobs
while(True) {
// some code
  if (haveNewTasks()) {
    allJobs.addJobWithLaunch(newJob(scope))
  }
// some code
}
```
