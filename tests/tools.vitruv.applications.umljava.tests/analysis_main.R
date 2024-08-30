library(ggplot2)
library(MVN)
library(dplyr)

# Read data
data_raw <- read.csv('./results/statistics.csv')

# Input: # of edits
# Output: Time to process, ms
data_raw$times <- (data_raw$end - data_raw$start) / 1000000
data <- data_raw %>% 
  group_by(name) %>%
  summarise(deltas = mean(deltas), meanT = mean(times), sd = sd(times)) %>%
  rename(times = meanT)

# Provide descriptive statistics
summary(data$deltas)
summary(data$times)

# Normal distribution? Questionable
# W = 0.82471, p = 0.01814
normalDistributionD <- shapiro.test(data$deltas)
# W = 0.84978, p = 0.03649
normalDistributionT <- shapiro.test(data$times)
# Multivariate normal distribution
normalDistribution <- mvn(data[c('deltas', 'times')], mvnTest = 'hz')

# Try Pearson correlation test
# r = 0.9844753
correlationET <- cor(x = data$deltas, y = data$times, method = "pearson")
cor.test(x = data$deltas, y = data$times, alternative = "greater", method = "pearson")

# Repeat for Spearman
# rho = 0.972028
correlationET_Spearman <- cor(x = data$deltas, y = data$times, method = "spearman")
cor.test(x = data$deltas, y = data$times, alternative = "greater", method = "spearman")

# Linear regression
# time = 47.759 + 0.3118 * edits
regressionETModel <- lm(times ~ deltas, data)
summary(regressionETModel)

# Plot distribution
scatterplot <- ggplot(data, mapping = aes(x = deltas, y = times)) +
  geom_point(shape=1) +
  geom_smooth(se = TRUE, method = lm) +
  coord_cartesian(clip = "off") +
  labs(fill = "Number of model deltas", x = "Edits", y = "Time (ms)")

print(scatterplot)

# Export
ggsave('./distribution_ET.pdf', plot = scatterplot, device = 'pdf', path = NULL,
  width = 9.6, height = 6.7, units = "cm")