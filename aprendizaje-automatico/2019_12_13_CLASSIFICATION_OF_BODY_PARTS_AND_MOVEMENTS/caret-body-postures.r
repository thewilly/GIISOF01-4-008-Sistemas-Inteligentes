# Eliminamos los warnings del script ya que ensucian el script
options(warn=-1)

# Realizamos los imports necesarios
library(caret)

load("har.RData")

# Configuramos nuestro identificador universitario
uo <- 236856
n_instancias <- 10000

set.seed(uo) #Pon aquí los número de tu UO
filtered <- har[sample(nrow(har)-1,n_instancias),]

filtered$age<-NULL
filtered$user<-NULL
filtered$gender<-NULL
filtered$how_tall_in_meters<-NULL
filtered$weight<-NULL
filtered$body_mass_index<-NULL

# Mostramos los datos con los que trabajaremos.
head(filtered)

training_percentage <- .8

set.seed(uo)
inTrain <- createDataPartition(
    # indicamos la clase
    y = filtered$class,
    
    # el porcentaje de instancias para entrenamiento
    p = training_percentage,
    
    # controla el formato de salida
    list = FALSE
)

# Definimos nuestras particiones de entrenamiento y validación.
training <- filtered[ inTrain,]
testing <- filtered[-inTrain,]

crossValidation <- trainControl(method = "cv", number = 10)

cat('Número de datos de cada clase: \n')
summary(filtered$class)
cat('\nPorcentaje de datos de cada clase: \n')
summary(filtered$class)/sum(summary(filtered$class))

# Creamos una función para ver los resultados de la validación interna
internalValidation <- function(model) {
  print(model)
  print(model$results[rownames(model$bestTune),1:4])
}

# Creamos una función para ver los resultados de la validación externa
externalValidation <- function(model, testingData = testing) {
  print(confusionMatrix(predict(model,testingData),testingData$class))
}

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo con J48.
c4.5Fit <- train(
    class ~ .,
    data = training,
    method = "J48",
    trControl = crossValidation,
    tuneLength = 7,
    metric = "Kappa"
)

# Resultados validación interna
internalValidation(c4.5Fit)

# Resultados validación externa
externalValidation(c4.5Fit)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo con rpart.
rpartFit <- train(
    class ~ .,
    data = training,
    method = "rpart",
    trControl = crossValidation,
    tuneLength = 10,
    metric = "Kappa"
)

# Resultados validación interna
internalValidation(rpartFit)

# Resultados validación externa
externalValidation(rpartFit)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo con rpart2.
rpart2Fit <- train(
    class ~ .,
    data = training,
    method = "rpart2",
    trControl = crossValidation,
    tuneLength = 21,
    metric = "Kappa"
)

# Resultados validación interna
internalValidation(rpart2Fit)

# Resultados validación externa
externalValidation(rpart2Fit)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo con knn.
knnFit <- train(
  class ~ .,
  data = training,
  method = "knn",
  trControl = crossValidation,
  tuneLength = 10, # k. Número de vecinos a comprobar.
  metric = "Kappa"
)

# Resultados validación interna
internalValidation(knnFit)

# Resultados validación externa
externalValidation(knnFit)

plotNNErrorEvolution <- function(nnModel){
  ggplot() + geom_line(aes(x=1:length(nnModel$finalModel$IterativeFitError), 
                           y=nnModel$finalModel$IterativeFitError)) +
  xlab("Iteraciones") + ylab("Error") 
}

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo
nn1LFit <- train(
    class ~ .,
    data = training,
    method = "mlp",
    trControl = crossValidation,
    metric = "Kappa",
    preProcess=c("center","scale"),
    tuneGrid = data.frame(size=seq(9,30,3))
)

nneurons1L <- nn1LFit$results[rownames(nn1LFit$bestTune),1]

# Resultados validación interna
internalValidation(nn1LFit)

externalValidation(nn1LFit)

# Dibujamos la evolución del error a lo largo de las iteraciones de la red
plotNNErrorEvolution(nn1LFit)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo
nn1LFit2_maxit300 <- train(
    class ~ .,
    data = training,
    method = "mlp",
    trControl = crossValidation,
    metric = "Kappa",
    preProcess=c("center","scale"),
    tuneGrid = data.frame(size=seq(9,30,3)),
    maxit=300
)

# Resultados validación interna
internalValidation(nn1LFit2_maxit300)

externalValidation(nn1LFit2_maxit300)

# Dibujamos la evolución del error a lo largo de las iteraciones de la red
plotNNErrorEvolution(nn1LFit2_maxit300)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo
nn1LFit2_maxit300_lr02 <- train(
    class ~ .,
    data = training,
    method = "mlp",
    trControl = crossValidation,
    metric = "Kappa",
    preProcess=c("center","scale"),
    tuneGrid = data.frame(size=seq(9,30,3)),
    maxit=300,
    leanFuncParam = c(0.01,0)
)

externalValidation(nn1LFit2_maxit300_lr02)

# Dibujamos la evolución del error a lo largo de las iteraciones de la red
plotNNErrorEvolution(nn1LFit2_maxit300_lr02)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo
nn2LFit <- train(
    class ~ .,
    data = training,
    method = "mlpML",
    trControl = crossValidation,
    tuneGrid = expand.grid(layer1=seq(10,14,2),
                           layer2=seq(10,14,2),
                           layer3=0)
)

internalValidation(nn2LFit)

externalValidation(nn2LFit)

# Dibujamos la evolución del error a lo largo de las iteraciones de la red
plotNNErrorEvolution(nn2LFit)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

# Realizamos el entrenamiento de nuestro modelo
nn2LFit_500 <- train(
    class ~ .,
    data = training,
    method = "mlpML",
    trControl = crossValidation,
    learnFuncParams = c(0.02,0),
    maxit = 500,
    metric = "Kappa",
    tuneGrid = expand.grid(layer1=seq(10,14,2),
                           layer2=seq(10,14,2),
                           layer3=0)
)

internalValidation(nn2LFit)

externalValidation(nn2LFit_500)

# Dibujamos la evolución del error a lo largo de las iteraciones de la red
plotNNErrorEvolution(nn2LFit_500)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

svmLinealFit <- train(
    class ~ .,
    data = training,
    method = "svmLinear",
    trControl = crossValidation,
    tuneGrid = data.frame(C=c(0.5, 1, 5, 10, 20, 40, 80)),
    metric = "kappa"
)

# Resultados validación interna
internalValidation(svmLinealFit)

# Resultados validación externa
externalValidation(svmLinealFit)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

svmPolyFit <- train(
    class ~ .,
    data = training,
    method = "svmPoly",
    trControl = crossValidation,
    tuneGrid = expand.grid(degree=c(1,3,5,10),C=c(1,3,5),scale = c(0.1, 0.5, 1)),
    metric = "kappa"
)

# Resultados validación interna
internalValidation(svmPolyFit)
externalValidation(svmPolyFit)

# Plantamos la semilla para obtener un valor constante tras cada ejecución.
set.seed(uo)

svmRadialFit <- train(
    class ~ .,
    data = training,
    method = "svmRadial",
    trControl = crossValidation, 
    tuneGrid = expand.grid(sigma=c(1,3),C=c(1,5,6,7)),
    metric = "kappa"
)

# Resultados validación interna
internalValidation(svmRadialFit)
externalValidation(svmRadialFit)

set.seed(uo)
rfFit <- train(
    class ~ .,
    data = training,
    method = "rf",
    trControl = crossValidation,
    tuneGrid = data.frame(mtry=seq(2,30,4)),
    metric = "Kappa"
)

rfFit$finalModel
# Resultados validación interna
internalValidation(rfFit)
externalValidation(rfFit)

kappaRF <- 0.9832
kappaKNN <- 0.9658
kappaSVM <- 0.959
kappaNN <- 0.9362
kappaDT <- 0.924

binomialTest <- function(percentageSuccess1, percentageSuccess2, testingData = testing){
  binom.test(
    round(c(percentageSuccess1, 1-percentageSuccess1)* # porcentaje de acierto y error 
            nrow(testingData)), # multiplicado por el número de instancias
                                # para obtener el número total de aciertos y fallos 
    p = percentageSuccess2 # el porcentaje de acierto del modelo con el que comparar
  ) 
}

binomialTest(kappaRF, kappaKNN) # Comparación NN y SVM

binomialTest(kappaRF, kappaSVM) # Comparación NN y RF

binomialTest(kappaRF, kappaNN) # Comparación NN y kNN

options(repr.plot.width=15, repr.plot.height=10)
dfm.resamp <- data.frame(Accuracy = c(nn1LFit$resample$Kappa,
                                      c4.5Fit$resample$Kappa,
                                      knnFit$resample$Kappa,
                                      svmPolyFit$resample$Kappa,
                                      rfFit$resample$Kappa), 
                        Model = rep(c('NeuralNet','C4.5','kNN', 'MVS', 'rF'), each=10))
ggplot(dfm.resamp,aes(x=Model, y=Accuracy, fill=Model)) + geom_boxplot() +
ggtitle("Distribución del porcetaje de acierto en el proceso de aprendizaje para cada paradigma") +
theme(plot.title = element_text(hjust = 0.5))


