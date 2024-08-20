library(ggplot2)

# Read data
data <- read.csv('./results/statistics.csv')

# Input: # of edits
# Output: Time to process, ms
data$times <- (data$end - data$start) / 1000000

# Normal distribution? Questionable
# W = 0.85178, p = 0.06099
normalDistributionE <- shapiro.test(data$steps)
# W = 0.87111, p = 0.103
normalDistribuitonT <- shapiro.test(data$times)

# Try Pearson correlation test
# r = 0.955171
correlationET <- cor(x = data$steps, y = data$times, method = "pearson")
cor.test(x = data$steps, y = data$times, alternative = "greater", method = "pearson")

# Repeat for Spearman
# rho = 0.9030303
correlationET_Spearman <- cor(x = data$steps, y = data$times, method = "spearman")
cor.test(x = data$steps, y = data$times, alternative = "greater", method = "spearman")

# Linear regression
# time = 47.759 + 0.3118 * edits
regressionETModel <- lm(times ~ steps, data)

# Plot distribution
scatterplot <- ggplot(data, mapping = aes(x = steps, y = times)) +
  geom_point(shape=1) +
  geom_smooth(se = FALSE, method = lm) +
  ggtitle('Number of primitive edit steps') +
  labs(fill = "Number of primitive edit steps", x = "Edits", y = "Time in ms")

print(scatterplot)
