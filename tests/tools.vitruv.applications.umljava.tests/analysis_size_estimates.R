library(dplyr)

# Sample size estimation
sample_size <- function(mean, sd, err, p = 0.05) {
  ceiling(((sd * qnorm(p/2)) / (mean * err)) ^ 2)
}

# Read data for sample size estimation
data_estimate <- read.csv('./results/statistics_estimates.csv')

# Input: # of edits
# Output: Time to process, ms
data_estimate$times <- (data_estimate$end - data_estimate$start) / 1000000

# Work on normalized data; mean and standard deviation for times, sample sizes
data_estimate_normalized <- data_estimate %>% 
  group_by(name) %>%
  summarise(deltas = mean(deltas), meanT = mean(times), sd = sd(times),
            sample_sizes = sample_size(meanT, sd, 0.02))
