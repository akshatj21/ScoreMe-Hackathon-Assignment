public class DocumentValidator {

    private static final Logger log = LoggerFactory.getLogger(DocumentValidator.class);

    public ValidationResult validate(Document doc) {
        try {
            if (doc == null) {
                throw new RuntimeException("Document is null");
            }
            String content = doc.extractContent();
            if (content.isEmpty()) {
                throw new RuntimeException("Empty content");
            }
            return runValidationRules(content);

        } catch (Exception e) {
            log.error("Unexpected error during validation", e); // FIX: we need structred logging instead of stack trace
            throw e; // FIX : throw the error instead of swallowing it
        }
    }

    public void validateBatch(List<Document> docs) {
        for (Document doc : docs) {
            try {
                ValidationResult r = validate(doc);
                if (r != null && r.isValid()) { // FIX: check for null values
                    saveResult(r);
                }
            } catch (Exception e) {
                // FIX: added logs for visibility
                log.error("Error processing document in batch", e);
            }
        }
    }
}
