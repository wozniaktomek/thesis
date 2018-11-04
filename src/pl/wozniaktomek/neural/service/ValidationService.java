package pl.wozniaktomek.neural.service;

import pl.wozniaktomek.neural.util.NeuralObject;
import pl.wozniaktomek.neural.util.Parameters;

import java.util.ArrayList;

public class ValidationService {
    private Parameters parameters;

    public ValidationService(Parameters parameters) {
        this.parameters = parameters;
    }

    public boolean validateObjects(ArrayList<NeuralObject> objectsLearning, ArrayList<NeuralObject> objectsTesting) {
        return validateEmptiness(objectsLearning, objectsTesting) &&
                validateSize(objectsLearning, objectsTesting) &&
                validateInputSize(objectsLearning, objectsTesting) &&
                validateClassAmount(objectsLearning, objectsTesting);
    }

    private boolean validateEmptiness(ArrayList<NeuralObject> objectsLearning, ArrayList<NeuralObject> objectsTesting) {
        return objectsLearning != null && objectsTesting != null;
    }

    private boolean validateSize(ArrayList<NeuralObject> objectsLearning, ArrayList<NeuralObject> objectsTesting) {
        return objectsLearning.size() > 0 && objectsTesting.size() > 0;
    }

    private boolean validateInputSize(ArrayList<NeuralObject> objectsLearning, ArrayList<NeuralObject> objectsTesting) {
        int inputSize = objectsLearning.get(0).getInputValues().size();

        for (NeuralObject neuralObject : objectsLearning) {
            if (neuralObject.getInputValues().size() != inputSize) {
                return false;
            }
        }

        for (NeuralObject neuralObject : objectsTesting) {
            if (neuralObject.getInputValues().size() != inputSize) {
                return false;
            }
        }

        parameters.setInputSize(inputSize);
        return true;
    }

    private boolean validateClassAmount(ArrayList<NeuralObject> objectsLearning, ArrayList<NeuralObject> objectsTesting) {
        int highestLearningClass = 0;
        int highestTestingClass = 0;

        for (NeuralObject neuralObject : objectsLearning) {
            if (neuralObject.getClassNumber() > highestLearningClass) {
                highestLearningClass = neuralObject.getClassNumber();
            }
        }

        for (NeuralObject neuralObject : objectsTesting) {
            if (neuralObject.getClassNumber() > highestTestingClass) {
                highestTestingClass = neuralObject.getClassNumber();
            }
        }

        if (highestLearningClass == highestTestingClass) {
            parameters.setOutputSize(highestLearningClass);
            return true;
        } else {
            return false;
        }
    }
}